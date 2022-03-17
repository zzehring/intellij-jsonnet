package com.github.zzehring.intellijjsonnet

import com.github.zzehring.intellijjsonnet.releases.Asset
import com.github.zzehring.intellijjsonnet.releases.RepoRelease
import com.github.zzehring.intellijjsonnet.settings.JLSSettingsStateComponent
import com.intellij.notification.*
import com.intellij.openapi.application.PathManager
import com.intellij.openapi.application.PreloadingActivity
import com.intellij.openapi.diagnostic.Logger
import com.intellij.openapi.progress.ProgressIndicator
import com.intellij.openapi.project.ProjectManager
import com.intellij.util.system.CpuArch
import com.intellij.util.text.SemVer
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import kotlinx.coroutines.*
import org.wso2.lsp4intellij.IntellijLanguageClient
import org.wso2.lsp4intellij.IntellijLanguageClient.getProjectToLanguageWrappers
import org.wso2.lsp4intellij.client.languageserver.serverdefinition.RawCommandServerDefinition
import org.wso2.lsp4intellij.listeners.LSPProjectManagerListener
import org.wso2.lsp4intellij.utils.FileUtils
import java.io.File
import java.net.URL
import java.nio.file.attribute.PosixFilePermissions
import java.util.concurrent.TimeUnit
import kotlin.io.path.Path
import kotlin.io.path.setPosixFilePermissions

const val EXTENSIONS = "jsonnet,libsonnet"

data class TargetReleaseInfo(val tag: String, val downloadUrl: String)

object JsonnetLSPreloadingActivity : PreloadingActivity() {
    private val LOG = Logger.getInstance(
        LSPProjectManagerListener::class.java
    )
    override fun preload(indicator: ProgressIndicator) {
        val languageServerRepo = JLSSettingsStateComponent.instance.state.releaseRepository
        val platform = getPlatform()
        val arch = getArch()

        LOG.info("Running on -> platform: $platform ; arch: $arch")

        val releaseURL = URL("https://api.github.com/repos/${languageServerRepo}/releases/latest")

        LOG.info("Repo/release URL: $releaseURL")

        val httpClient = HttpClient(CIO) {
            expectSuccess = false
            install(JsonFeature) {
                serializer = KotlinxSerializer(kotlinx.serialization.json.Json {
                    prettyPrint = true
                    isLenient = true
                })
            }
        }

        val repoInfo = getLatestReleaseInfo(httpClient, releaseURL, platform, arch)
        LOG.info("Latest tag: ${repoInfo.tag} ; Download URL: ${repoInfo.downloadUrl}")

        val binFile = File(PathManager.getPluginsPath().plus("/Jsonnet Language Server/jsonnet-language-server"))

        // Check if LS binary already exists. If it does and the latest release is a higher version, prompt user to update
        // If binary doesn't exist, download latest
        if (binFile.exists() && upgradeAvailable(binFile, repoInfo.tag)) {
            presentUpdateBalloon(httpClient, binFile, repoInfo)
        } else if (!binFile.exists()) {
            download(httpClient, binFile, repoInfo)
        }

        setExecutablePerms(binFile)

        // Configure language server
        // TODO: Make --tanka configurable
        // TODO: add JPath configuration
        IntellijLanguageClient.addServerDefinition(
            RawCommandServerDefinition(
                EXTENSIONS,
                arrayOf(binFile.toString(), "--tanka", "--lint"))
        )
    }

    // Returns false if binary version < latest version. True if latest tag is higher
    private fun upgradeAvailable(binFile: File, tag: String): Boolean {
        val p = ProcessBuilder(binFile.toString(), "--version")
            .redirectOutput(ProcessBuilder.Redirect.PIPE)
            .redirectError(ProcessBuilder.Redirect.PIPE)
            .start()
        p.waitFor(60, TimeUnit.SECONDS)
        val versionOutput = p.inputStream.bufferedReader().readText().trim()
        val currentVersion = SemVer.parseFromText(versionOutput.split(" ").last())
        val latestVersion = SemVer.parseFromText(tag.trimStart('v'))
        LOG.info("Getting comparison between $currentVersion and $tag")
        return if (currentVersion != null && latestVersion != null) {
            !currentVersion.isGreaterOrEqualThan(latestVersion)
        } else {
            false
        }
    }

    private fun presentUpdateBalloon(httpClient: HttpClient, binFile: File, repoInfo: TargetReleaseInfo) {
        val project = ProjectManager.getInstance().defaultProject
        Notification("lsp", "New jsonnet-language-server version (${repoInfo.tag}) available. Would you like to update?", NotificationType.IDE_UPDATE)
            .addAction(NotificationAction.createSimpleExpiring("Update") { download(httpClient, binFile, repoInfo) })
            .addAction(NotificationAction.createSimpleExpiring("Cancel") { LOG.info("User declined update.") })
            .notify(project)
    }

    private fun getPlatform(): String {
        val os = System.getProperty("os.name").lowercase()
        return when {
            os.contains("win") -> {
                "windows"
            }
            os.contains("nix") || os.contains("nux") || os.contains("aix") -> {
                "linux"
            }
            os.contains("mac") -> {
                "darwin"
            }
            else -> "linux"
        }
    }

    private fun getArch(): String {
        val arch = CpuArch.CURRENT
        return when {
            arch.equals(CpuArch.ARM64) -> {
                "arm64"
            }
            arch.equals(CpuArch.X86_64) -> {
                "amd64"
            }
            else -> "amd64"
        }
    }

    private fun getLatestReleaseInfo(httpClient: HttpClient, releaseURL: URL, platform: String, arch: String): TargetReleaseInfo {
        var binaryDownloadUrl = ""
        var latestTag: String
        runBlocking {
            val release: RepoRelease = httpClient.get(releaseURL)
            latestTag = release.tag_name
            for (asset: Asset in release.assets) {
                if (arch in asset.name && platform in asset.name) {
                    binaryDownloadUrl = asset.browser_download_url
                    break
                }
            }
        }
        return TargetReleaseInfo(tag = latestTag, downloadUrl = binaryDownloadUrl)
    }

    private fun download(httpClient: HttpClient, binFile: File, repoInfo: TargetReleaseInfo) {
        runBlocking {
            stopServers()
            val httpResponse: HttpResponse = httpClient.get(repoInfo.downloadUrl)
            val responseBody: ByteArray = httpResponse.receive()
            binFile.writeBytes(responseBody)
            LOG.info("Saved binary to ${binFile.name}")
            val project = ProjectManager.getInstance().defaultProject
            Notification("lsp", "Language Server jsonnet-language-server (version ${repoInfo.tag}) downloaded", NotificationType.IDE_UPDATE)
                .notify(project)
            FileUtils.reloadAllEditors()
        }
    }

    private fun stopServers() {
        getProjectToLanguageWrappers().forEach { (_, servers) ->
            servers.forEach { server ->
                LOG.info("Stopping server with status:'${server.status}' and project: ${server.project})}")
                server.stop(true)
                LOG.info("Stopped server. New status:'${server.status}'")
            }
        }
    }

    private fun setExecutablePerms(file: File) {
        val executablePerms = PosixFilePermissions.fromString("rwxr--r--")
        Path(file.toString()).setPosixFilePermissions(executablePerms)
    }

}

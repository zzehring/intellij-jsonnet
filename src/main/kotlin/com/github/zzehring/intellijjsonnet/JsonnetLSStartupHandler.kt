package com.github.zzehring.intellijjsonnet

import com.github.zzehring.intellijjsonnet.releases.Asset
import com.github.zzehring.intellijjsonnet.releases.RepoRelease
import com.github.zzehring.intellijjsonnet.settings.JLSSettingsStateComponent
import com.intellij.notification.Notification
import com.intellij.notification.NotificationAction
import com.intellij.notification.NotificationType
import com.intellij.openapi.application.PathManager
import com.intellij.openapi.diagnostic.Logger
import com.intellij.openapi.project.ProjectManager
import com.intellij.util.net.HttpConfigurable
import com.intellij.util.system.CpuArch
import com.intellij.util.text.SemVer
import io.ktor.client.HttpClient
import io.ktor.client.call.*
import io.ktor.client.engine.*
import io.ktor.client.engine.ProxyBuilder
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json
import org.wso2.lsp4intellij.IntellijLanguageClient
import org.wso2.lsp4intellij.client.languageserver.serverdefinition.RawCommandServerDefinition
import org.wso2.lsp4intellij.listeners.LSPProjectManagerListener
import org.wso2.lsp4intellij.utils.FileUtils
import java.io.File
import java.net.URL
import java.nio.file.Files
import java.nio.file.InvalidPathException
import java.nio.file.Paths
import java.nio.file.attribute.PosixFileAttributeView
import java.nio.file.attribute.PosixFilePermissions
import java.util.concurrent.TimeUnit
import kotlin.io.path.Path
import kotlin.io.path.setPosixFilePermissions

const val EXTENSIONS = "jsonnet,libsonnet"

data class TargetReleaseInfo(val tag: String, val downloadUrl: String)

class JsonnetLSStartupHandler {

    private val log = Logger.getInstance(
        LSPProjectManagerListener::class.java
    )

    fun start() {

        val languageServerRepo = JLSSettingsStateComponent.instance.state.releaseRepository
        val enableEvalDiagnostics = JLSSettingsStateComponent.instance.state.enableEvalDiagnostics
        val enableLintDiagnostics = JLSSettingsStateComponent.instance.state.enableLintDiagnostics
        val jpaths = JLSSettingsStateComponent.instance.state.jPaths
        val platform = getPlatform()
        val arch = getArch()

        log.info("Running on -> platform: $platform ; arch: $arch")

        val releaseURL = URL("https://api.github.com/repos/${languageServerRepo}/releases/latest")

        log.info("Repo/release URL: $releaseURL")

        val httpClient = HttpClient(CIO) {
            expectSuccess = false
            engine {
                val proxySettings = HttpConfigurable.getInstance()
                val proxyHost = proxySettings.PROXY_HOST

                if (proxySettings != null && proxySettings.USE_HTTP_PROXY && proxyHost.isNotEmpty()) {
                    proxy = ProxyBuilder.http("http://${proxyHost}:${proxySettings.PROXY_PORT}/")
                }
            }
            install(ContentNegotiation) {
                json(Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                })
            }
        }

        val repoInfo = getLatestReleaseInfo(httpClient, releaseURL, platform, arch)
        log.info("Latest tag: ${repoInfo.tag} ; Download URL: ${repoInfo.downloadUrl}")

        val binFile = File(PathManager.getPluginsPath().plus("/jsonnet-language-server/jsonnet-language-server"))

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
        var optionalArgs = arrayOf<String>()
        if (enableEvalDiagnostics) {
            optionalArgs += "--eval-diags"
        }
        if (enableLintDiagnostics) {
            optionalArgs += "--lint"
        }
        // Add jpaths to optionalArgs, if jpath is valid path
        for (jpath in jpaths) {
            if (jpath.isNotEmpty() && isValidPath(jpath)) {
                optionalArgs += "--jpath"
                optionalArgs += jpath
            } else {
                log.warn("Invalid jpath: $jpath")
            }
        }
        IntellijLanguageClient.addServerDefinition(
            RawCommandServerDefinition(
                EXTENSIONS,
                arrayOf(binFile.toString(), "--tanka", *optionalArgs)
            )
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
        log.info("Getting comparison between $currentVersion and $tag")
        return if (currentVersion != null && latestVersion != null) {
            !currentVersion.isGreaterOrEqualThan(latestVersion)
        } else {
            false
        }
    }

    private fun presentUpdateBalloon(httpClient: HttpClient, binFile: File, repoInfo: TargetReleaseInfo) {
        val project = ProjectManager.getInstance().defaultProject
        Notification(
            "lsp",
            "New jsonnet-language-server version (${repoInfo.tag}) available. Would you like to update?",
            NotificationType.IDE_UPDATE
        )
            .addAction(NotificationAction.createSimpleExpiring("Update") { download(httpClient, binFile, repoInfo) })
            .addAction(NotificationAction.createSimpleExpiring("Cancel") { log.info("User declined update.") })
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

    private fun getLatestReleaseInfo(
        httpClient: HttpClient,
        releaseURL: URL,
        platform: String,
        arch: String
    ): TargetReleaseInfo {
        var binaryDownloadUrl = ""
        var latestTag: String
        runBlocking {
            val release: RepoRelease = httpClient.get(releaseURL).body()
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
            val responseBody: ByteArray = httpResponse.body()
            binFile.writeBytes(responseBody)
            log.info("Saved binary to ${binFile.name}")
            val project = ProjectManager.getInstance().defaultProject
            Notification(
                "lsp",
                "Language Server jsonnet-language-server (version ${repoInfo.tag}) downloaded",
                NotificationType.IDE_UPDATE
            )
                .notify(project)
            FileUtils.reloadAllEditors()
        }
    }

    private fun stopServers() {
        IntellijLanguageClient.getProjectToLanguageWrappers().forEach { (_, servers) ->
            servers.forEach { server ->
                log.info("Stopping server with status:'${server.status}' and project: ${server.project})}")
                server.stop(true)
                log.info("Stopped server. New status:'${server.status}'")
            }
        }
    }

    private fun setExecutablePerms(file: File) {
        if (Files.getFileAttributeView(file.toPath(), PosixFileAttributeView::class.java) != null) {
            val executablePerms = PosixFilePermissions.fromString("rwxr--r--")
            Path(file.toString()).setPosixFilePermissions(executablePerms)
        } else {
            file.setReadable(true)
            file.setWritable(true, true)
            file.setExecutable(true, true)
        }
    }

    private fun isValidPath(path: String): Boolean {
        return try {
            Paths.get(path)
            true
        } catch (e: InvalidPathException) {
            false
        }
    }

}

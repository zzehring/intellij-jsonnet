package com.github.zzehring.intellijjsonnet

import com.github.zzehring.intellijjsonnet.settings.JLSSettingsStateComponent
import com.intellij.notification.Notification
import com.intellij.notification.NotificationAction
import com.intellij.notification.NotificationType
import com.intellij.openapi.diagnostic.Logger
import com.intellij.openapi.progress.ProgressIndicator
import com.intellij.openapi.project.Project
import com.intellij.util.system.CpuArch
import com.redhat.devtools.lsp4ij.installation.ServerInstallerBase
import com.redhat.devtools.lsp4ij.installation.download.GitHubAssetFetcher
import com.redhat.devtools.lsp4ij.installation.download.Reporter
import java.io.File
import java.io.FileOutputStream
import java.net.HttpURLConnection
import java.net.URL
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.nio.file.attribute.PosixFileAttributeView
import java.nio.file.attribute.PosixFilePermissions
import java.util.concurrent.TimeUnit

/**
 * Server installer for Jsonnet Language Server using lsp4ij's installation API.
 * Downloads the server binary from GitHub releases (grafana/jsonnet-language-server).
 */
class JsonnetServerInstaller : ServerInstallerBase() {

    private val log = Logger.getInstance(JsonnetServerInstaller::class.java)

    companion object {
        private const val DEFAULT_OWNER = "grafana"
        private const val DEFAULT_REPO = "jsonnet-language-server"

        fun getInstallPath(): Path {
            val userHome = System.getProperty("user.home")
            return Paths.get(userHome, ".lsp4ij", "lsp", "jsonnet-language-server")
        }

        fun getBinaryFile(): File {
            val binaryName = if (System.getProperty("os.name").lowercase().contains("win")) {
                "jsonnet-language-server.exe"
            } else {
                "jsonnet-language-server"
            }
            return getInstallPath().resolve(binaryName).toFile()
        }
    }

    override fun getProject(): Project? = null

    override fun checkServerInstalled(indicator: ProgressIndicator): Boolean {
        progressCheckingServerInstalled(indicator)
        val binaryFile = getBinaryFile()
        val installed = binaryFile.exists() && binaryFile.canExecute()

        if (installed) {
            // Check for updates in background (non-blocking)
            checkForUpdateAsync(binaryFile)
        }

        return installed
    }

    override fun install(indicator: ProgressIndicator) {
        progressInstallingServer(indicator)

        val settings = JLSSettingsStateComponent.instance.state
        val repoPath = settings.releaseRepository
        val (owner, repo) = parseRepository(repoPath)

        val platform = getPlatform()
        val arch = getArch()

        log.info("Installing jsonnet-language-server for platform: $platform, arch: $arch")
        progress("Fetching release information...", indicator)

        // Use lsp4ij's GitHubAssetFetcher to find the download URL
        val fetcher = GitHubAssetFetcher(owner, repo)
        val assetPattern = "*${platform}*${arch}*"

        // Create a reporter that updates the progress indicator
        val reporter = object : Reporter {
            override fun setText(text: String) {
                progress(text, indicator)
                log.info(text)
            }

            override fun setText(text: String, error: Exception) {
                progress(text, indicator)
                log.error(text, error)
            }

            override fun checkCanceled() {
                indicator.checkCanceled()
            }
        }

        val downloadUrl = fetcher.getDownloadUrl(
            { asset ->
                val name = asset.get("name")?.asString ?: ""
                name.contains(platform) && name.contains(arch)
            },
            reporter
        )

        if (downloadUrl == null) {
            throw RuntimeException("Could not find download URL for jsonnet-language-server (pattern: $assetPattern)")
        }

        log.info("Downloading from: $downloadUrl")
        progress("Downloading jsonnet-language-server...", indicator)

        // Create installation directory
        val installPath = getInstallPath()
        Files.createDirectories(installPath)

        val binaryFile = getBinaryFile()

        // Download the binary
        downloadFile(URL(downloadUrl), binaryFile, indicator)

        // Set executable permissions
        setExecutablePermissions(binaryFile)

        log.info("Successfully installed jsonnet-language-server to: ${binaryFile.absolutePath}")
        progress("Installation complete", indicator)

        Notification(
            "lsp",
            "Jsonnet Language Server installed successfully",
            NotificationType.INFORMATION
        ).notify(null)
    }

    private fun checkForUpdateAsync(binaryFile: File) {
        Thread {
            try {
                checkForUpdate(binaryFile)
            } catch (e: Exception) {
                log.warn("Failed to check for updates", e)
            }
        }.start()
    }

    private fun checkForUpdate(binaryFile: File) {
        val settings = JLSSettingsStateComponent.instance.state
        val (owner, repo) = parseRepository(settings.releaseRepository)

        val currentVersion = getCurrentVersion(binaryFile) ?: return
        val latestVersion = getLatestVersion(owner, repo) ?: return

        if (isNewerVersion(latestVersion, currentVersion)) {
            Notification(
                "lsp",
                "Jsonnet Language Server",
                "A new version ($latestVersion) is available. Current: $currentVersion",
                NotificationType.IDE_UPDATE
            )
                .addAction(NotificationAction.createSimpleExpiring("Update") {
                    // User can reinstall via lsp4ij's reinstall action
                    Notification(
                        "lsp",
                        "Use the 'Reinstall' action in the LSP Explorer to update",
                        NotificationType.INFORMATION
                    ).notify(null)
                })
                .addAction(NotificationAction.createSimpleExpiring("Dismiss") {})
                .notify(null)
        }
    }

    private fun getCurrentVersion(binaryFile: File): String? {
        return try {
            val process = ProcessBuilder(binaryFile.absolutePath, "--version")
                .redirectOutput(ProcessBuilder.Redirect.PIPE)
                .redirectError(ProcessBuilder.Redirect.PIPE)
                .start()
            process.waitFor(30, TimeUnit.SECONDS)
            val output = process.inputStream.bufferedReader().readText().trim()
            // Output format: "jsonnet-language-server version v0.x.x" or similar
            output.split(" ").lastOrNull()?.removePrefix("v")
        } catch (e: Exception) {
            log.warn("Failed to get current version", e)
            null
        }
    }

    private fun getLatestVersion(owner: String, repo: String): String? {
        return try {
            val url = URL("https://api.github.com/repos/$owner/$repo/releases/latest")
            val connection = url.openConnection() as HttpURLConnection
            connection.setRequestProperty("Accept", "application/vnd.github.v3+json")
            connection.connectTimeout = 5000
            connection.readTimeout = 5000

            val response = connection.inputStream.bufferedReader().readText()
            // Simple JSON parsing for tag_name
            val tagMatch = Regex("\"tag_name\"\\s*:\\s*\"([^\"]+)\"").find(response)
            tagMatch?.groupValues?.get(1)?.removePrefix("v")
        } catch (e: Exception) {
            log.warn("Failed to get latest version", e)
            null
        }
    }

    private fun isNewerVersion(latest: String, current: String): Boolean {
        return try {
            val latestParts = latest.split(".").map { it.toIntOrNull() ?: 0 }
            val currentParts = current.split(".").map { it.toIntOrNull() ?: 0 }

            for (i in 0 until maxOf(latestParts.size, currentParts.size)) {
                val l = latestParts.getOrElse(i) { 0 }
                val c = currentParts.getOrElse(i) { 0 }
                if (l > c) return true
                if (l < c) return false
            }
            false
        } catch (e: Exception) {
            false
        }
    }

    private fun downloadFile(url: URL, destination: File, indicator: ProgressIndicator) {
        val connection = url.openConnection() as HttpURLConnection
        connection.connectTimeout = 30000
        connection.readTimeout = 60000

        val totalSize = connection.contentLengthLong
        var downloadedSize = 0L

        connection.inputStream.use { input ->
            FileOutputStream(destination).use { output ->
                val buffer = ByteArray(8192)
                var bytesRead: Int
                while (input.read(buffer).also { bytesRead = it } != -1) {
                    output.write(buffer, 0, bytesRead)
                    downloadedSize += bytesRead
                    if (totalSize > 0) {
                        val fraction = downloadedSize.toDouble() / totalSize
                        progress("Downloading... ${(fraction * 100).toInt()}%", fraction, indicator)
                    }
                }
            }
        }
    }

    private fun setExecutablePermissions(file: File) {
        if (Files.getFileAttributeView(file.toPath(), PosixFileAttributeView::class.java) != null) {
            val executablePerms = PosixFilePermissions.fromString("rwxr-xr-x")
            Files.setPosixFilePermissions(file.toPath(), executablePerms)
        } else {
            file.setReadable(true)
            file.setWritable(true, true)
            file.setExecutable(true, true)
        }
    }

    private fun parseRepository(repoPath: String): Pair<String, String> {
        val parts = repoPath.split("/")
        return if (parts.size >= 2) {
            Pair(parts[0], parts[1])
        } else {
            Pair(DEFAULT_OWNER, DEFAULT_REPO)
        }
    }

    private fun getPlatform(): String {
        val os = System.getProperty("os.name").lowercase()
        return when {
            os.contains("win") -> "windows"
            os.contains("nix") || os.contains("nux") || os.contains("aix") -> "linux"
            os.contains("mac") -> "darwin"
            else -> "linux"
        }
    }

    private fun getArch(): String {
        return when (CpuArch.CURRENT) {
            CpuArch.ARM64 -> "arm64"
            CpuArch.X86_64 -> "amd64"
            else -> "amd64"
        }
    }
}

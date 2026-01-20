package com.github.zzehring.intellijjsonnet

import com.github.zzehring.intellijjsonnet.settings.JLSSettingsStateComponent
import com.intellij.execution.configurations.GeneralCommandLine
import com.intellij.openapi.diagnostic.Logger
import com.intellij.openapi.project.Project
import com.redhat.devtools.lsp4ij.server.OSProcessStreamConnectionProvider
import java.nio.file.InvalidPathException
import java.nio.file.Paths

/**
 * Connection provider for the Jsonnet Language Server.
 * The server binary is managed by JsonnetServerInstaller.
 */
class JsonnetLanguageServer(private val project: Project) : OSProcessStreamConnectionProvider() {

    private val log = Logger.getInstance(JsonnetLanguageServer::class.java)

    init {
        val binaryFile = JsonnetServerInstaller.getBinaryFile()
        val commandLine = createCommandLine(binaryFile.absolutePath)
        setCommandLine(commandLine)
    }

    private fun createCommandLine(binaryPath: String): GeneralCommandLine {
        val settings = JLSSettingsStateComponent.instance.state
        val commandLine = GeneralCommandLine(binaryPath)

        // Always add --tanka flag
        commandLine.addParameter("--tanka")

        // Add optional flags based on settings
        if (settings.enableEvalDiagnostics) {
            commandLine.addParameter("--eval-diags")
        }

        if (settings.enableLintDiagnostics) {
            commandLine.addParameter("--lint")
        }

        // Add jpaths
        for (jpath in settings.jPaths) {
            if (jpath.isNotEmpty() && isValidPath(jpath)) {
                commandLine.addParameter("--jpath")
                commandLine.addParameter(jpath)
            } else if (jpath.isNotEmpty()) {
                log.warn("Invalid jpath: $jpath")
            }
        }

        log.info("Starting jsonnet-language-server with command: ${commandLine.commandLineString}")
        return commandLine
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

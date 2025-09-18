package com.github.zzehring.intellijjsonnet

import com.github.zzehring.intellijjsonnet.settings.JLSSettingsStateComponent
import com.intellij.codeInsight.daemon.DaemonCodeAnalyzer
import com.intellij.openapi.diagnostic.Logger
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.project.Project
import com.intellij.util.FileContentUtilCore;
import org.eclipse.lsp4j.DidChangeConfigurationParams
import org.wso2.lsp4intellij.IntellijLanguageClient
import org.wso2.lsp4intellij.listeners.LSPProjectManagerListener


data class TargetReleaseInfo(val tag: String, val downloadUrl: String)

class JsonnetLSStartupHandler {

    private val log = Logger.getInstance(
        LSPProjectManagerListener::class.java
    )

    fun start(project: Project) {
        log.info("Starting Jsonnet Language Server ...")
        val settingsState = project.getService(JLSSettingsStateComponent::class.java).state
        val downloader = JsonnetLSDownloader(settingsState.releaseRepository)
        val executablePath = downloader.getOrPrepareExecutable()

        // Configure language server
        // TODO: Make --tanka configurable
        IntellijLanguageClient.addServerDefinition(
            JsonnetLSDefinition(
                arrayOf(executablePath),
                settingsState.asSettings()
            ),
            project
        )
    }

    fun onSettingsChanged(project: Project, settings: Any) {
        log.info("Settings changed")
        // Notify language server about the change
        IntellijLanguageClient.didChangeConfiguration(DidChangeConfigurationParams(settings), project)

        // Apply changes to opened editors
        DaemonCodeAnalyzer.getInstance(project).restart()
        val openFiles = FileEditorManager.getInstance(project).allEditors.filter {
            it.file.fileType is JsonnetFileType
        }.map {
            it.file
        }
        FileContentUtilCore.reparseFiles(openFiles)
    }

}

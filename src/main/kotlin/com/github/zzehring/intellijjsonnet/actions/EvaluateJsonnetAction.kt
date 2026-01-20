package com.github.zzehring.intellijjsonnet.actions

import com.intellij.notification.Notification
import com.intellij.notification.NotificationType
import com.intellij.openapi.actionSystem.ActionUpdateThread
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.PlatformDataKeys
import com.intellij.openapi.fileEditor.FileEditorManager
import com.intellij.openapi.util.io.FileUtilRt
import com.intellij.openapi.vfs.VfsUtil
import com.redhat.devtools.lsp4ij.commands.CommandExecutor
import com.redhat.devtools.lsp4ij.commands.LSPCommandContext
import org.eclipse.lsp4j.Command
import org.jetbrains.annotations.NotNull


class EvaluateJsonnetAction : AnAction() {
    override fun getActionUpdateThread(): ActionUpdateThread {
        return ActionUpdateThread.BGT
    }

    override fun update(@NotNull event: AnActionEvent) {
        val openedFile = event.getData(PlatformDataKeys.VIRTUAL_FILE)
        event.presentation.isEnabledAndVisible = openedFile != null && openedFile.extension == "jsonnet"
    }

    override fun actionPerformed(@NotNull event: AnActionEvent) {
        val tmpDir = FileUtilRt.createTempDirectory("jsonnet-plugin-tmpdir", null)
        val tmpResultFile = FileUtilRt.createTempFile(tmpDir, "jsonnet-eval", ".json")
        val openedFile = event.getData(PlatformDataKeys.VIRTUAL_FILE)
        val project = event.project ?: return

        try {
            // Create LSP command with file path as argument
            val command = Command("Evaluate Jsonnet File", "jsonnet.evalFile", listOf(openedFile!!.path))

            // Create command context and specify our language server
            val commandContext = LSPCommandContext(command, project)
            commandContext.preferredLanguageServerId = "jsonnetLanguageServerId"

            // Execute the command
            CommandExecutor.executeCommand(commandContext)
                .response()
                ?.thenAccept { result ->
                    if (result != null) {
                        tmpResultFile.writeText(result.toString())
                        val vf = VfsUtil.findFileByIoFile(tmpResultFile, true)
                        FileEditorManager.getInstance(project).openFile(vf!!, true)
                    }
                }
                ?.exceptionally { throwable ->
                    Notification(
                        "lsp",
                        "Failed to evaluate Jsonnet file: ${throwable.message}",
                        NotificationType.ERROR
                    ).notify(project)
                    null
                }
        } catch (e: Exception) {
            Notification(
                "lsp",
                "Failed to evaluate Jsonnet file: ${e.message}",
                NotificationType.ERROR
            ).notify(project)
        }
    }
}
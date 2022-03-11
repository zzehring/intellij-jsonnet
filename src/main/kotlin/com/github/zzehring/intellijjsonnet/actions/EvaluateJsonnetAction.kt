package com.github.zzehring.intellijjsonnet.actions

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.PlatformDataKeys
import com.intellij.openapi.fileEditor.FileEditorManager
import com.intellij.openapi.util.io.FileUtilRt
import com.intellij.openapi.vfs.VfsUtil
import org.eclipse.lsp4j.ExecuteCommandParams
import org.jetbrains.annotations.NotNull
import org.wso2.lsp4intellij.IntellijLanguageClient
import org.wso2.lsp4intellij.utils.FileUtils


class EvaluateJsonnetAction : AnAction() {
    override fun update(@NotNull event: AnActionEvent) {
        val openedFile = event.getData(PlatformDataKeys.VIRTUAL_FILE)
        event.presentation.isEnabledAndVisible = openedFile != null && openedFile.extension == "jsonnet"
    }

    override fun actionPerformed(@NotNull event: AnActionEvent) {
        val tmpDir = FileUtilRt.createTempDirectory("jsonnet-plugin-tmpdir", null)
        val tmpResultFile = FileUtilRt.createTempFile(tmpDir, "jsonnet-eval", ".json")
        val openedFile = event.getData(PlatformDataKeys.VIRTUAL_FILE)
        val project = event.project
        val params = ExecuteCommandParams("jsonnet.evalFile", listOf(openedFile!!.path))
        IntellijLanguageClient.getAllServerWrappersFor(FileUtils.projectToUri(project)).forEach { x ->
            val execution = x.requestManager.executeCommand(params)
            val result: String = execution.get() as String
            tmpResultFile.writeText(result)
        }
        val vf = VfsUtil.findFileByIoFile(tmpResultFile, true)
        FileEditorManager.getInstance(project!!).openFile(vf!!, true)
    }
}
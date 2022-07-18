package com.github.zzehring.intellijjsonnet

import com.github.zzehring.intellijjsonnet.psi.JsonnetFile
import com.github.zzehring.intellijjsonnet.utils.MyEditorEventManager
import com.intellij.formatting.service.AsyncDocumentFormattingService
import com.intellij.formatting.service.AsyncFormattingRequest
import com.intellij.formatting.service.FormattingService
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.fileEditor.FileEditorManager
import com.intellij.psi.PsiFile
import org.wso2.lsp4intellij.editor.EditorEventManagerBase
import java.util.*


class JsonnetExternalFormatter : AsyncDocumentFormattingService() {
    override fun getFeatures(): MutableSet<FormattingService.Feature> {
        return EnumSet.noneOf(FormattingService.Feature::class.java)
    }

    override fun canFormat(file: PsiFile): Boolean {
        return file is JsonnetFile
    }

    override fun createFormattingTask(formattingRequest: AsyncFormattingRequest): FormattingTask {
        val editor = FileEditorManager.getInstance(formattingRequest.context.project).selectedTextEditor
        return JsonnetFormattingTask(editor!!, formattingRequest)
    }

    override fun getNotificationGroupId(): String {
        return "lsp"
    }

    override fun getName(): String {
        return "Jsonnet Language Server Format"
    }

    class JsonnetFormattingTask(private val editor: Editor, private val asyncRequest: AsyncFormattingRequest) : FormattingTask {
        override fun run() {
            val em = EditorEventManagerBase.forEditor(editor)
            val myEm = MyEditorEventManager(editor, em.requestManager, em.project)
            // Using custom reformat code to workaround same-line ordering problem:
            // When the language server returns edits, often the edit entries will have the same range/span (i.e. same line).
            // However, the order of the edits is still correct for insertion. But, the edits in lsp4intellij are applied bottom to top
            // so the result ordering is incorrect. To fix this, custom reformat logic is added to reverse the order
            // of entries with the same line/range/span which achieves the correct order of inserts.
            myEm.reformat()
            // the above reformat() actually edits the document, but we call onTextReady() anyway or else we'd receive
            // an error about task not finishing after 30s.
            asyncRequest.onTextReady(editor.document.text)
        }

        override fun cancel(): Boolean {
            return true
        }

    }
}
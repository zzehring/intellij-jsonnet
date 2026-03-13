package com.github.zzehring.intellijjsonnet.utils

import com.intellij.openapi.command.CommandProcessor
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.fileEditor.FileDocumentManager
import com.intellij.openapi.project.Project
import com.github.zzehring.intellijjsonnet.settings.JLSSettingsStateComponent
import org.apache.commons.lang3.StringUtils
import org.eclipse.lsp4j.DidChangeConfigurationParams
import org.eclipse.lsp4j.DocumentFormattingParams
import org.eclipse.lsp4j.FormattingOptions
import org.eclipse.lsp4j.TextDocumentIdentifier
import org.eclipse.lsp4j.TextEdit
import org.wso2.lsp4intellij.client.languageserver.requestmanager.RequestManager
import org.wso2.lsp4intellij.utils.ApplicationUtils
import org.wso2.lsp4intellij.utils.DocumentUtils
import java.util.concurrent.CompletableFuture
import java.util.function.Consumer

class MyEditorEventManager(
    private val editor: Editor,
    private val requestManager: RequestManager,
    private val project: Project
) {
    private val identifier = TextDocumentIdentifier(org.wso2.lsp4intellij.utils.FileUtils.editorToURIString(editor))

    /**
     * Reformat the whole document
     */
    fun reformat() {
        ApplicationUtils.pool {
            // Send the user's configured quote style to the LSP server
            val formatting = JLSSettingsStateComponent.instance.state.formatting
            val conf = mapOf(
                "formatting" to mapOf(
                    "StringStyle" to formatting.quoteStyle,
                    "CommentStyle" to formatting.commentStyle,
                    "Indent" to formatting.indent,
                    "MaxBlankLines" to formatting.maxBlankLines,
                    "PrettyFieldNames" to formatting.prettyFieldNames,
                    "UseImplicitPlus" to formatting.useImplicitPlus,
                    "PadArrays" to formatting.padArrays,
                    "PadObjects" to formatting.padObjects,
                    "SortImports" to formatting.sortImports
                )
            )
            requestManager.didChangeConfiguration(DidChangeConfigurationParams(conf))

            if (editor.isDisposed) {
                return@pool
            }
            val params = DocumentFormattingParams()
            params.textDocument = identifier
            val options = FormattingOptions()
            options.tabSize = DocumentUtils.getTabSize(editor)
            options.isInsertSpaces = DocumentUtils.shouldUseSpaces(editor)
            params.options = options
            val request: CompletableFuture<List<TextEdit?>> = requestManager.formatting(params) ?: return@pool
            request.thenAccept { formatting: List<TextEdit?>? ->
                if (formatting != null) {
                    ApplicationUtils.invokeLater {
                        applyEdit(
                            formatting as List<TextEdit>,
                            "Reformat document",
                        )
                    }
                }
            }
        }
    }

    /**
     * Returns a Runnable used to apply the given edits and save the document
     * Used by WorkspaceEditHandler (allows to revert a rename for example)
     *
     * @param edits   The edits
     * @return The runnable
     */
    private fun getEditsRunnable(edits: List<TextEdit>?): Runnable? {
        if (edits == null) {
            return null
        }
        val document = editor.document
        if (!document.isWritable) {
            return null
        }
        return Runnable {

            // Creates a sorted edit list based on the insertion position and the edits will be applied from the bottom
            // to the top of the document. Otherwise all the other edit ranges will be invalid after the very first edit,
            // since the document is changed.
            val lspEdits: MutableList<LSPTextEdit> = ArrayList()
            edits.forEach(Consumer { edit: TextEdit ->
                val text = edit.newText
                val range = edit.range
                if (range != null) {
                    val start = DocumentUtils.LSPPosToOffset(editor, range.start)
                    val end = DocumentUtils.LSPPosToOffset(editor, range.end)
                    lspEdits.add(LSPTextEdit(text, start, end))
                }
            })

            // Sort according to the start offset, in descending order.
            lspEdits.sort()

            // Reverse sort entries on same line
            // Ex:
            // original -> [A(1), B(2), C(3), D(3), E(3) F(3), G(4)]
            // after -> [A(1), B(2), F(3), E(3), D(3), C(3), G(4)]
            var cur = 0
            while (cur < lspEdits.size - 1) {
                val left = cur
                while (lspEdits[cur].startOffset == lspEdits[cur + 1].startOffset) {
                    cur++
                }
                if (left < cur) {
                    reverseSort(lspEdits, left, cur)
                }
                cur += 1
            }

            lspEdits.forEach(Consumer { edit: LSPTextEdit ->
                var text = edit.text
                val start = edit.startOffset
                val end = edit.endOffset
                if (StringUtils.isEmpty(text)) {
                    document.deleteString(start, end)
                } else {
                    text = text.replace(DocumentUtils.WIN_SEPARATOR, DocumentUtils.LINUX_SEPARATOR)
                    if (end >= 0) {
                        if (end - start <= 0) {
                            document.insertString(start, text)
                        } else {
                            document.replaceString(start, end, text)
                        }
                    } else if (start == 0) {
                        document.setText(text)
                    } else if (start > 0) {
                        document.insertString(start, text)
                    }
                }
                FileDocumentManager.getInstance().saveDocument(editor.document)
            })
        }
    }

    private fun reverseSort(lspEdits: MutableList<LSPTextEdit>, left: Int, right: Int) {
        var i = left
        var j = right
        while (i < j) {
            val temp = lspEdits[i]
            lspEdits[i] = lspEdits[j]
            lspEdits[j] = temp
            i++
            j--
        }
    }

    private class LSPTextEdit constructor(val text: String, val startOffset: Int, val endOffset: Int) :
        Comparable<LSPTextEdit?> {

        override fun compareTo(other: LSPTextEdit?): Int {
            return other!!.startOffset - startOffset
        }
    }

    /**
     * Applies the given edits to the document
     *
     * @param edits      The edits to apply
     * @param name       The name of the edits (Rename, for example)
     * @return True if the edits were applied, false otherwise
     */
    private fun applyEdit(
        edits: List<TextEdit>,
        name: String?,
    ): Boolean {
        val runnable = getEditsRunnable(edits)
        ApplicationUtils.writeAction {
            if (runnable != null) {
                CommandProcessor.getInstance()
                    .executeCommand(project, runnable, name, "LSPPlugin", editor.document)
            }
        }
        return runnable != null
    }
}

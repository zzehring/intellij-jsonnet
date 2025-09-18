package com.github.zzehring.intellijjsonnet.settings

import com.github.zzehring.intellijjsonnet.JsonnetFileType
import com.intellij.openapi.ui.Splitter
import com.intellij.ui.TableUtil
import com.intellij.ui.ToolbarDecorator
import com.intellij.ui.components.JBCheckBox
import com.intellij.ui.components.JBLabel
import com.intellij.ui.components.JBTextField
import com.intellij.ui.table.JBTable
import com.intellij.ui.EditorTextField
import com.intellij.util.ui.FormBuilder
import com.intellij.util.ui.JBUI
import org.jetbrains.annotations.NotNull
import java.awt.BorderLayout
import javax.swing.JComponent
import javax.swing.JPanel
import javax.swing.JScrollPane
import javax.swing.event.TableModelEvent
import javax.swing.table.DefaultTableModel
import javax.swing.table.TableModel

/**
 * Supports creating and managing a {@link JPanel} for the Settings Dialog
 */
class JLSSettingsComponent {
    var settingsPanel: JPanel
    var jPathsPanel: JPanel
    private val releaseRepository = JBTextField()
    var extVarsPanel: JPanel
    var extCodesPanel: JPanel
    private val enableLintDiagnostics = JBCheckBox("Enable lint diagnostics on language server")
    private val enableEvalDiagnostics = JBCheckBox("Enable eval diagnostics on language")
    private val jPathsTableModel = DefaultTableModel(arrayOf("JPath"), 0)
    private val extVarsTableModel = DefaultTableModel(arrayOf("var", "value"), 0)
    // For extCodes we keep only the variable names in the table; values are edited in a proper code editor on the right
    private val extCodesNamesModel = DefaultTableModel(arrayOf("var"), 0)
    private val extCodesMap = linkedMapOf<String, String>()

    init {
        this.settingsPanel = FormBuilder.createFormBuilder()
            .addLabeledComponent(JBLabel("Release Repo (Github Repository from which to download language server): "), releaseRepository, 1, true)
            .addComponent(enableEvalDiagnostics)
            .addTooltip("Try to evaluate files to find errors and warnings. Disable on large projects to improve performance. IDE restart required.")
            .addComponent(enableLintDiagnostics)
            .addTooltip("Enable live linting diagnostics. Disable on large projects to improve performance. IDE restart required.")
            .panel

        val jPathsTable = JBTable(jPathsTableModel)
        val tablePanel = ToolbarDecorator.createDecorator(jPathsTable)
            .setAddAction {
                jPathsTableModel.addRow(arrayOf())
                TableUtil.editCellAt(jPathsTable, jPathsTableModel.rowCount - 1, 0)
            }
            .setRemoveAction {
                for (i in jPathsTable.selectedRows.reversed()) {
                    jPathsTableModel.removeRow(i)
                }
            }
            .createPanel()
        jPathsPanel = FormBuilder.createFormBuilder()
            .addLabeledComponent(JBLabel("JPaths (Additional directories to search for jsonnet library files): "), tablePanel, 1, true)
            .panel
        jPathsTable.border = JBUI.Borders.emptyTop(20)

        val extVarsTable = JBTable(extVarsTableModel)
        val extVarTablePanel = ToolbarDecorator.createDecorator(extVarsTable)
            .setAddAction {
                extVarsTableModel.addRow(arrayOf())
                TableUtil.editCellAt(extVarsTable, extVarsTableModel.rowCount - 1, 0)
            }
            .setRemoveAction {
                for (i in extVarsTable.selectedRows.reversed()) {
                    extVarsTableModel.removeRow(i)
                }
            }
            .createPanel()
        extVarsPanel = FormBuilder.createFormBuilder()
            .addLabeledComponent(JBLabel("Ext vars (Provide the value as a string)"), extVarTablePanel, 1, true)
            .panel
        extVarsTable.border = JBUI.Borders.emptyTop(20)

        // Ext codes: list of variable names on the left, Jsonnet editor on the right
        val extCodesTable = JBTable(extCodesNamesModel)
        val extCodesListPanel = ToolbarDecorator.createDecorator(extCodesTable)
            .setAddAction {
                // Ensure current selection value is saved
                syncEditorWithCurrentSelection(extCodesTable)
                extCodesNamesModel.addRow(arrayOf(""))
                TableUtil.editCellAt(extCodesTable, extCodesNamesModel.rowCount - 1, 0)
            }
            .setRemoveAction {
                // save current editor content before removal
                syncEditorWithCurrentSelection(extCodesTable)
                for (row in extCodesTable.selectedRows.reversed()) {
                    val key = extCodesNamesModel.getValueAt(row, 0)?.toString()
                    if (key != null) extCodesMap.remove(key)
                    extCodesNamesModel.removeRow(row)
                }
            }
            .createPanel()

        val codeEditor = EditorTextField("", null, JsonnetFileType.INSTANCE)
        codeEditor.setOneLineMode(false)
        codeEditor.isViewer = false
        codeEditor.autoscrolls = true
        codeEditor.border = JBUI.Borders.empty(5)

        // Update map whenever the code editor text changes for the selected key
        codeEditor.document.addDocumentListener(object : com.intellij.openapi.editor.event.DocumentListener {
            override fun documentChanged(event: com.intellij.openapi.editor.event.DocumentEvent) {
                val row = extCodesTable.selectedRow
                if (row >= 0) {
                    val key = extCodesNamesModel.getValueAt(row, 0)?.toString().orEmpty()
                    if (key.isNotEmpty()) {
                        extCodesMap[key] = codeEditor.text
                    }
                }
            }
        })

        // When table selection changes, swap editor content
        extCodesTable.selectionModel.addListSelectionListener {
            val row = extCodesTable.selectedRow
            if (row >= 0) {
                val key = extCodesNamesModel.getValueAt(row, 0)?.toString().orEmpty()
                codeEditor.text = extCodesMap[key].orEmpty()
                codeEditor.isEnabled = true
            } else {
                codeEditor.text = ""
                codeEditor.isEnabled = false
            }
        }

        // Track renames in the left table to preserve code mapping
        extCodesNamesModel.addTableModelListener { e ->
            if (e.type == TableModelEvent.UPDATE && e.column == 0 && e.firstRow == e.lastRow) {
                val row = e.firstRow
                // We can't get old value from the event easily; instead, before updating we synced editor, so
                // we just ensure the code is associated with the new key
                val newKey = extCodesNamesModel.getValueAt(row, 0)?.toString().orEmpty()
                // If there is no entry for newKey, keep current editor text under newKey
                if (!extCodesMap.containsKey(newKey)) {
                    extCodesMap[newKey] = codeEditor.text
                }
            }
        }

        val rightPanel = JPanel(BorderLayout()).apply {
            add(JBLabel("Jsonnet code for selected variable:"), BorderLayout.NORTH)
            add(codeEditor, BorderLayout.CENTER)
        }

        val splitter = Splitter(false, 0.33f).apply {
            firstComponent = extCodesListPanel
            secondComponent = rightPanel
        }

        extCodesPanel = FormBuilder.createFormBuilder()
            .addLabeledComponent(JBLabel("Ext codes (Provide a value as Jsonnet code)"), splitter, 1, true)
            .panel
        extCodesTable.border = JBUI.Borders.emptyTop(20)
    }

    private fun syncEditorWithCurrentSelection(extCodesTable: JBTable) {
        val row = extCodesTable.selectedRow
        if (row >= 0) {
            val key = extCodesNamesModel.getValueAt(row, 0)?.toString().orEmpty()
            if (key.isNotEmpty()) {
                // Obtain current editor content from the right side component
                val editor = (extCodesPanel.components.lastOrNull() as? JPanel)
                    ?.components
                    ?.filterIsInstance<EditorTextField>()
                    ?.firstOrNull()
                if (editor != null) {
                    extCodesMap[key] = editor.text
                }
            }
        }
    }

    fun getPreferredFocusedComponent(): JComponent {
        return releaseRepository
    }

    @NotNull
    fun getReleaseRepository(): String {
        return releaseRepository.text
    }

    fun setReleaseRepository(newPath: String) {
        releaseRepository.text = newPath
    }

    fun getEnableLintDiagnostics(): Boolean {
        return enableLintDiagnostics.isSelected
    }

    fun setEnableLintDiagnostics(isSelected: Boolean) {
        enableLintDiagnostics.isSelected = isSelected
    }

    fun getEnableEvalDiagnostics(): Boolean {
        return enableEvalDiagnostics.isSelected
    }

    fun setEnableEvalDiagnostics(isSelected: Boolean) {
        enableEvalDiagnostics.isSelected = isSelected
    }

    fun getJPaths(): List<String> {
        val paths = mutableListOf<String>()
        for (i in 0 until jPathsTableModel.rowCount) {
            val aPath = jPathsTableModel.getValueAt(i, 0)
            paths.add(aPath.toString())
        }
        return paths
    }

    fun setJPaths(paths: List<String>) {
        jPathsTableModel.rowCount = 0
        for (path in paths) {
            jPathsTableModel.addRow(arrayOf(path))
        }
    }

    fun getExtVars(): Map<String, String> = tableModelGetMap(extVarsTableModel)

    fun setExtVars(extVars: Map<String, String>) = tableModelSetMap(extVars, extVarsTableModel)

    fun getExtCodes(): Map<String, String> {
        // ensure current editor content is saved
        // Find ext codes table by traversing extCodesPanel components
        // This is a bit hacky but keeps changes minimal without refactoring class structure
        val tables = extCodesPanel.components.flatMap { comp ->
            if (comp is JPanel) comp.components.toList() else listOf(comp)
        }.filterIsInstance<JBTable>()
        tables.firstOrNull()?.let { syncEditorWithCurrentSelection(it) }
        // Return a copy to avoid exposing internal map
        return LinkedHashMap(extCodesMap.filterKeys { it.isNotEmpty() })
    }

    fun setExtCodes(extCodes: Map<String, String>) {
        extCodesNamesModel.rowCount = 0
        extCodesMap.clear()
        for ((key, value) in extCodes) {
            extCodesNamesModel.addRow(arrayOf(key))
            extCodesMap[key] = value
        }
    }

    private fun tableModelGetMap(model: TableModel): Map<String, String> {
        val map = mutableMapOf<String, String>()
        for (i in 0 until model.rowCount) {
            val key = model.getValueAt(i, 0)?.toString()
            val value = model.getValueAt(i, 1)?.toString()
            if (key != null && value != null) {
                map[key] = value
            }
        }
        return map
    }

    private fun tableModelSetMap(map: Map<String, String>, model: DefaultTableModel) {
        model.rowCount = 0
        for ((key, value) in map) {
            model.addRow(arrayOf(key, value))
        }
    }
}
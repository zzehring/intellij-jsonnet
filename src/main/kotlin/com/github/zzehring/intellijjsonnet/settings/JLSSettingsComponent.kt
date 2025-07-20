package com.github.zzehring.intellijjsonnet.settings

import com.intellij.ui.TableUtil
import com.intellij.ui.ToolbarDecorator
import com.intellij.ui.components.JBCheckBox
import com.intellij.ui.components.JBLabel
import com.intellij.ui.components.JBTextField
import com.intellij.ui.table.JBTable
import com.intellij.util.ui.FormBuilder
import org.jetbrains.annotations.NotNull
import javax.swing.JComponent
import javax.swing.JPanel
import javax.swing.table.DefaultTableModel
import javax.swing.JComboBox

/**
 * Supports creating and managing a {@link JPanel} for the Settings Dialog
 */
class JLSSettingsComponent {
    var settingsPanel: JPanel
    var jPathsPanel: JPanel
    private val releaseRepository = JBTextField()
    private val enableLintDiagnostics = JBCheckBox("Enable lint diagnostics on language server")
    private val enableEvalDiagnostics = JBCheckBox("Enable eval diagnostics on language")
    private val jPathsTableModel = DefaultTableModel(arrayOf("JPath"), 0)
    private val quoteStyleComboBox = JComboBox(arrayOf("leave", "single", "double"))

    init {
        this.settingsPanel = FormBuilder.createFormBuilder()
            .addLabeledComponent(JBLabel("Release Repo (Github Repository from which to download language server): "), releaseRepository, 1, true)
            .addComponent(enableEvalDiagnostics)
            .addTooltip("Try to evaluate files to find errors and warnings. Disable on large projects to improve performance. IDE restart required.")
            .addComponent(enableLintDiagnostics)
            .addTooltip("Enable live linting diagnostics. Disable on large projects to improve performance. IDE restart required.")
            .addLabeledComponent(JBLabel("String Quote Style: "), quoteStyleComboBox, 1, true)
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

    fun getQuoteStyle(): String {
        return quoteStyleComboBox.selectedItem as String
    }

    fun setQuoteStyle(style: String) {
        quoteStyleComboBox.selectedItem = style
    }
}

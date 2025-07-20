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
import javax.swing.JSeparator
import java.awt.GridBagLayout
import java.awt.GridBagConstraints
import java.awt.GridLayout
import javax.swing.BoxLayout

/**
 * Supports creating and managing a {@link JPanel} for the Settings Dialog
 */
class JLSFormattingOptionsComponent {
    val panel: JPanel
    private val quoteStyleComboBox = JComboBox(arrayOf("leave", "single", "double"))
    private val commentStyleComboBox = JComboBox(arrayOf("leave", "hash", "slash"))
    private val indentField = JBTextField("2", 3)
    private val maxBlankLinesField = JBTextField("2", 3)
    private val prettyFieldNamesCheckBox = JBCheckBox("Pretty Field Names", true)
    private val useImplicitPlusCheckBox = JBCheckBox("Use Implicit Plus", true)
    private val padArraysCheckBox = JBCheckBox("Pad Arrays", false)
    private val padObjectsCheckBox = JBCheckBox("Pad Objects", true)
    private val sortImportsCheckBox = JBCheckBox("Sort Imports", true)

    init {
        val leftPanel = FormBuilder.createFormBuilder()
            .addLabeledComponent(JBLabel("String Quote Style:"), quoteStyleComboBox)
            .addLabeledComponent(JBLabel("Comment Style:"), commentStyleComboBox)
            .addLabeledComponent(JBLabel("Indent:"), indentField)
            .addLabeledComponent(JBLabel("Max Blank Lines:"), maxBlankLinesField)
            .panel

        val rightPanel = FormBuilder.createFormBuilder()
            .addComponent(prettyFieldNamesCheckBox)
            .addComponent(useImplicitPlusCheckBox)
            .addComponent(padArraysCheckBox)
            .addComponent(padObjectsCheckBox)
            .addComponent(sortImportsCheckBox)
            .panel

        val contentPanel = JPanel()
        contentPanel.layout = GridLayout(1, 2, 16, 0)
        contentPanel.add(leftPanel)
        contentPanel.add(rightPanel)

        val headerPanel = FormBuilder.createFormBuilder()
            .addComponent(JSeparator())
            .addComponent(JBLabel("<html><b>Formatting Options</b></html>"))
            .panel

        panel = JPanel()
        panel.layout = BoxLayout(panel, BoxLayout.Y_AXIS)
        panel.add(headerPanel)
        panel.add(contentPanel)
    }

    fun getFormatting(): JLSSettingsStateComponent.FormattingConfiguration {
        return JLSSettingsStateComponent.FormattingConfiguration(
            quoteStyle = quoteStyleComboBox.selectedItem as String,
            commentStyle = commentStyleComboBox.selectedItem as String,
            indent = indentField.text.toIntOrNull() ?: 2,
            maxBlankLines = maxBlankLinesField.text.toIntOrNull() ?: 2,
            prettyFieldNames = prettyFieldNamesCheckBox.isSelected,
            useImplicitPlus = useImplicitPlusCheckBox.isSelected,
            padArrays = padArraysCheckBox.isSelected,
            padObjects = padObjectsCheckBox.isSelected,
            sortImports = sortImportsCheckBox.isSelected
        )
    }

    fun setFormatting(formatting: JLSSettingsStateComponent.FormattingConfiguration) {
        quoteStyleComboBox.selectedItem = formatting.quoteStyle
        commentStyleComboBox.selectedItem = formatting.commentStyle
        indentField.text = formatting.indent.toString()
        maxBlankLinesField.text = formatting.maxBlankLines.toString()
        prettyFieldNamesCheckBox.isSelected = formatting.prettyFieldNames
        useImplicitPlusCheckBox.isSelected = formatting.useImplicitPlus
        padArraysCheckBox.isSelected = formatting.padArrays
        padObjectsCheckBox.isSelected = formatting.padObjects
        sortImportsCheckBox.isSelected = formatting.sortImports
    }

}

class JLSSettingsComponent {
    var settingsPanel: JPanel
    var jPathsPanel: JPanel
    private val releaseRepository = JBTextField()
    private val enableLintDiagnostics = JBCheckBox("Enable lint diagnostics on language server")
    private val enableEvalDiagnostics = JBCheckBox("Enable eval diagnostics on language")
    private val jPathsTableModel = DefaultTableModel(arrayOf("JPath"), 0)
    private val formattingComponent = JLSFormattingOptionsComponent()

    init {
        this.settingsPanel = FormBuilder.createFormBuilder()
            .addLabeledComponent(JBLabel("Release Repo (Github Repository from which to download language server): "), releaseRepository, 1, true)
            .addComponent(enableEvalDiagnostics)
            .addTooltip("Try to evaluate files to find errors and warnings. Disable on large projects to improve performance. IDE restart required.")
            .addComponent(enableLintDiagnostics)
            .addTooltip("Enable live linting diagnostics. Disable on large projects to improve performance. IDE restart required.")
            .addComponent(formattingComponent.panel)
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

    fun getFormatting(): JLSSettingsStateComponent.FormattingConfiguration {
        return formattingComponent.getFormatting()
    }

    fun setFormatting(formatting: JLSSettingsStateComponent.FormattingConfiguration) {
        formattingComponent.setFormatting(formatting)
    }
}

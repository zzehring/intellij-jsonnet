package com.github.zzehring.intellijjsonnet.settings

import com.intellij.ui.components.JBCheckBox
import com.intellij.ui.components.JBLabel
import com.intellij.ui.components.JBTextField
import com.intellij.util.ui.FormBuilder
import org.jetbrains.annotations.NotNull
import javax.swing.JComponent
import javax.swing.JPanel

/**
 * Supports creating and managing a {@link JPanel} for the Settings Dialog
 */
class JLSSettingsComponent {
    var repoPanel: JPanel
    private val releaseRepository = JBTextField()
    private val enableLintDiagnostics = JBCheckBox("Enable lint diagnostics on language server")
    private val enableEvalDiagnostics = JBCheckBox("Enable eval diagnostics on language")

    init {
        this.repoPanel = FormBuilder.createFormBuilder()
            .addLabeledComponent(JBLabel("Release Repo (Github Repository from which to download language server): "), releaseRepository, 1, true)
            .addComponent(enableEvalDiagnostics)
            .addTooltip("Try to evaluate files to find errors and warnings. Disable on large projects to improve performance. IDE restart required.")
            .addComponent(enableLintDiagnostics)
            .addTooltip("Enable live linting diagnostics. Disable on large projects to improve performance. IDE restart required.")
            .addComponentFillVertically(JPanel(), 0)
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

}
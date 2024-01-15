package com.github.zzehring.intellijjsonnet.settings

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
    var myMainPanel: JPanel
    private val releaseRepository = JBTextField()
    private val jsonnetLanguageServerCustomPath = JBTextField()

    init {
        this.myMainPanel = FormBuilder.createFormBuilder()
            .addLabeledComponent(JBLabel("Release Repo (Github Repository from which to download language server): "), releaseRepository, 1, true)
            .addLabeledComponent(JBLabel("Custom path to language server binary: "), jsonnetLanguageServerCustomPath, 1, true)
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
    @NotNull
    fun getJsonnetLanguageServerCustomPath(): String {
        return jsonnetLanguageServerCustomPath.text
    }

    fun setReleaseRepository(newPath: String) {
        releaseRepository.text = newPath
    }
    fun setJsonnetLanguageServerCustomPath(newPath: String) {
        jsonnetLanguageServerCustomPath.text = newPath
    }

}
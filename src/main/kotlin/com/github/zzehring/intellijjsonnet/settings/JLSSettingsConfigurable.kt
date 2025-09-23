package com.github.zzehring.intellijjsonnet.settings

import com.intellij.openapi.components.service
import com.intellij.openapi.options.Configurable
import com.intellij.openapi.project.Project
import org.jetbrains.annotations.Nls
import org.jetbrains.annotations.Nullable
import java.awt.GridBagConstraints
import java.awt.GridBagLayout
import javax.swing.JComponent
import javax.swing.JPanel

class JLSSettingsConfigurable(private val project: Project) : Configurable {

    private lateinit var mySettingsComponent: JLSSettingsComponent

    @Nullable
    override fun createComponent(): JComponent {
        mySettingsComponent = JLSSettingsComponent()
        val containerPanel = JPanel(GridBagLayout())
        val constraints = GridBagConstraints()
        constraints.fill = GridBagConstraints.HORIZONTAL
        constraints.anchor = GridBagConstraints.NORTHWEST
        constraints.weightx = 1.0
        constraints.weighty = 1.0
        constraints.gridx = 0
        constraints.gridy = 0
        containerPanel.add(mySettingsComponent.settingsPanel, constraints)
        constraints.gridy = 1
        containerPanel.add(mySettingsComponent.jPathsPanel, constraints)
        constraints.gridy = 2
        containerPanel.add(mySettingsComponent.extVarsPanel, constraints)
        constraints.gridy = 3
        containerPanel.add(mySettingsComponent.extCodesPanel, constraints)
        return containerPanel
    }

    override fun isModified(): Boolean {
        val settings = project.service<JLSSettingsStateComponent>().state
        return mySettingsComponent.getReleaseRepository() != settings.releaseRepository
                || mySettingsComponent.getEnableEvalDiagnostics() != settings.enableEvalDiagnostics
                || mySettingsComponent.getEnableLintDiagnostics() != settings.enableLintDiagnostics
                || mySettingsComponent.getJPaths() != settings.jPaths
                || mySettingsComponent.getExtVars() != settings.extVars
                || mySettingsComponent.getExtCodes() != settings.extCodes
    }

    override fun apply() {
        val settings = project.service<JLSSettingsStateComponent>().state
        settings.releaseRepository = mySettingsComponent.getReleaseRepository()
        settings.enableEvalDiagnostics = mySettingsComponent.getEnableEvalDiagnostics()
        settings.enableLintDiagnostics = mySettingsComponent.getEnableLintDiagnostics()
        settings.jPaths = mySettingsComponent.getJPaths()
        settings.extVars = mySettingsComponent.getExtVars()
        settings.extCodes = mySettingsComponent.getExtCodes()
        // Notify listeners within this project that settings have changed
        project.messageBus.syncPublisher(JLSSettingsListener.TOPIC).onSettingsChanged(settings)
    }

    @Nls(capitalization = Nls.Capitalization.Title)
    override fun getDisplayName(): String {
        return "Jsonnet Language Server"
    }

    override fun getPreferredFocusedComponent(): JComponent {
        return mySettingsComponent.getPreferredFocusedComponent()
    }

    override fun reset() {
        val settings = project.service<JLSSettingsStateComponent>().state
        mySettingsComponent.setReleaseRepository(settings.releaseRepository)
        mySettingsComponent.setEnableEvalDiagnostics(settings.enableEvalDiagnostics)
        mySettingsComponent.setEnableLintDiagnostics(settings.enableLintDiagnostics)
        mySettingsComponent.setJPaths(settings.jPaths)
        mySettingsComponent.setExtVars(settings.extVars)
        mySettingsComponent.setExtCodes(settings.extCodes)
    }

}

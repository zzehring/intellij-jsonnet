package com.github.zzehring.intellijjsonnet.settings

import com.intellij.ide.projectWizard.NewProjectWizardCollector
import com.intellij.openapi.options.Configurable
import com.intellij.openapi.project.IntelliJProjectUtil
import com.intellij.openapi.project.Project
import org.eclipse.lsp4j.DidChangeConfigurationParams
import org.jetbrains.annotations.Nls
import org.jetbrains.annotations.Nullable
import org.wso2.lsp4intellij.IntellijLanguageClient
import java.awt.GridBagConstraints
import java.awt.GridBagLayout
import javax.swing.JComponent
import javax.swing.JPanel

class JLSSettingsConfigurable(private val project: Project) : Configurable {

    private lateinit var mySettingsComponent: JLSSettingsComponent

    @Nullable
    override fun createComponent(): JComponent {
        mySettingsComponent = JLSSettingsComponent()
        mySettingsComponent.setEnableLintDiagnostics(true)
        mySettingsComponent.setEnableEvalDiagnostics(false)
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
        return containerPanel
    }

    override fun isModified(): Boolean {
        val settings = JLSSettingsStateComponent.instance.state
        return mySettingsComponent.getReleaseRepository() != settings.releaseRepository
                || mySettingsComponent.getEnableEvalDiagnostics() != settings.enableEvalDiagnostics
                || mySettingsComponent.getEnableLintDiagnostics() != settings.enableLintDiagnostics
                || mySettingsComponent.getJPaths() != settings.jPaths
                || mySettingsComponent.getLocalLSPPath() != settings.localLSPPath
                || mySettingsComponent.getExtCode() != settings.extCode
    }

    override fun apply() {
        val settings = JLSSettingsStateComponent.instance.state
        settings.releaseRepository = mySettingsComponent.getReleaseRepository()
        settings.enableEvalDiagnostics = mySettingsComponent.getEnableEvalDiagnostics()
        settings.enableLintDiagnostics = mySettingsComponent.getEnableLintDiagnostics()
        settings.jPaths = mySettingsComponent.getJPaths()
        settings.extCode = mySettingsComponent.getExtCode()
        settings.localLSPPath = mySettingsComponent.getLocalLSPPath()

        val didChangeConfigurationParams = DidChangeConfigurationParams()
        didChangeConfigurationParams.settings = hashMapOf(
            "jpath" to mySettingsComponent.getJPaths(),
            "show_docstring_in_completion" to true,
            "ext_code" to mySettingsComponent.getExtCodeAsMap()
        )

        IntellijLanguageClient.didChangeConfiguration(
            didChangeConfigurationParams,
            project
        )
    }

    @Nls(capitalization = Nls.Capitalization.Title)
    override fun getDisplayName(): String {
        return "Jsonnet Language Server"
    }

    override fun getPreferredFocusedComponent(): JComponent {
        return mySettingsComponent.getPreferredFocusedComponent()
    }

    override fun reset() {
        val settings = JLSSettingsStateComponent.instance.state
        mySettingsComponent.setReleaseRepository(settings.releaseRepository)
        mySettingsComponent.setEnableEvalDiagnostics(settings.enableEvalDiagnostics)
        mySettingsComponent.setEnableLintDiagnostics(settings.enableLintDiagnostics)
        mySettingsComponent.setJPaths(settings.jPaths)
        mySettingsComponent.setExtCode(settings.extCode)
        mySettingsComponent.setLocalLSPPath(settings.localLSPPath)
    }

}

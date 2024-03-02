package com.github.zzehring.intellijjsonnet.settings

import com.intellij.openapi.options.Configurable
import org.jetbrains.annotations.Nls
import org.jetbrains.annotations.Nullable
import javax.swing.JComponent

class JLSSettingsConfigurable : Configurable {

    private lateinit var mySettingsComponent: JLSSettingsComponent

    @Nullable
    override fun createComponent(): JComponent {
        mySettingsComponent = JLSSettingsComponent()
        mySettingsComponent.setEnableLintDiagnostics(true)
        mySettingsComponent.setEnableEvalDiagnostics(false)
        return mySettingsComponent.repoPanel
    }

    override fun isModified(): Boolean {
        val settings = JLSSettingsStateComponent.instance.state
        return mySettingsComponent.getReleaseRepository() != settings.releaseRepository
                || mySettingsComponent.getEnableEvalDiagnostics() != settings.enableEvalDiagnostics
                || mySettingsComponent.getEnableLintDiagnostics() != settings.enableLintDiagnostics
    }

    override fun apply() {
        val settings = JLSSettingsStateComponent.instance.state
        settings.releaseRepository = mySettingsComponent.getReleaseRepository()
        settings.enableEvalDiagnostics = mySettingsComponent.getEnableEvalDiagnostics()
        settings.enableLintDiagnostics = mySettingsComponent.getEnableLintDiagnostics()
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
    }

}

package com.github.zzehring.intellijjsonnet.settings

import com.intellij.openapi.options.Configurable
import org.jetbrains.annotations.Nls
import org.jetbrains.annotations.Nullable
import javax.swing.JComponent

class JLSSettingsConfigurable : Configurable {

    private lateinit var mySettingsComponent: JLSSettingsComponent

    @Nullable
    override fun createComponent(): JComponent? {
        mySettingsComponent = JLSSettingsComponent()
        return mySettingsComponent.myMainPanel
    }

    override fun isModified(): Boolean {
        val settings = JLSSettingsStateComponent.instance.state
        return mySettingsComponent.getReleaseRepository() != settings.releaseRepository
    }

    override fun apply() {
        val settings = JLSSettingsStateComponent.instance.state
        settings.releaseRepository = mySettingsComponent.getReleaseRepository()
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
    }

}

package com.github.zzehring.intellijjsonnet.settings

import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.components.PersistentStateComponent
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage
import org.jetbrains.annotations.Nullable

@State(
    name = "com.github.zzehring.intellijjsonnet.JLSSettingsState",
    storages = [Storage("SdkSettingsPlugin.xml")]
)
open class JLSSettingsStateComponent : PersistentStateComponent<JLSSettingsStateComponent.SettingsState> {

    companion object {
        val instance: JLSSettingsStateComponent
            get() = ApplicationManager.getApplication().getService(JLSSettingsStateComponent::class.java)
    }

    var settingsState = SettingsState()

    @Nullable
    override fun getState(): SettingsState {
        return settingsState
    }

    override fun loadState(state: SettingsState) {
        settingsState = state
    }

    class SettingsState {
        var releaseRepository = "grafana/jsonnet-language-server"
        var jsonnetLanguageServerCustomPath = "path/to/binary/jsonnet-language-server"
    }
}
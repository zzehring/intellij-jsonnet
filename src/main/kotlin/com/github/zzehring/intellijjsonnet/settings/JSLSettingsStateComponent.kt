package com.github.zzehring.intellijjsonnet.settings

import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.components.PersistentStateComponent
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage
import org.jetbrains.annotations.Nullable

@State(
    name = "com.github.zzehring.intellijjsonnet.JLSSettingsState",
    storages = [Storage("JsonnetLsSettingsPlugin.xml")]
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

    data class FormattingConfiguration(
        var quoteStyle: String = "leave",
        var commentStyle: String = "leave",
        var indent: Int = 2,
        var maxBlankLines: Int = 2,
        var prettyFieldNames: Boolean = true,
        var useImplicitPlus: Boolean = true,
        var padArrays: Boolean = false,
        var padObjects: Boolean = true,
        var sortImports: Boolean = true
    )

    class SettingsState {
        var releaseRepository = "grafana/jsonnet-language-server"
        var enableLintDiagnostics = false
        var enableEvalDiagnostics = false
        var jPaths = listOf<String>()
        var formatting = FormattingConfiguration()
    }
}

package com.github.zzehring.intellijjsonnet.settings

import com.intellij.openapi.components.PersistentStateComponent
import com.intellij.openapi.components.Service
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage
import com.intellij.openapi.components.StoragePathMacros
import com.intellij.openapi.project.Project
import org.jetbrains.annotations.Nullable
import java.nio.file.InvalidPathException
import java.nio.file.Paths
import kotlin.text.isNotEmpty

@Service(Service.Level.PROJECT)
@State(
    name = "com.github.zzehring.intellijjsonnet.JLSSettingsState",
    // Store per-project. Use workspace.xml so machine-specific paths (like jpaths) are not shared via VCS.
    storages = [Storage(StoragePathMacros.WORKSPACE_FILE)]
)
class JLSSettingsStateComponent : PersistentStateComponent<JLSSettingsStateComponent.SettingsState> {

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
        var enableLintDiagnostics = false
        var enableEvalDiagnostics = false
        var jPaths = listOf<String>()
        var extVars = mapOf<String, String>()
        var extCodes = mapOf<String, String>()

        fun asSettings(): Map<String, Any> {
            return mapOf(
                "resolve_paths_with_tanka" to true,
                "enable_eval_diagnostics" to enableEvalDiagnostics,
                "enable_lint_diagnostics" to enableLintDiagnostics,
                "ext_vars" to extVars,
                "ext_code" to extCodes,
                "jpath" to jPaths.filter { it.isNotEmpty() && isValidPath(it) }
            )
        }

        private fun isValidPath(path: String): Boolean {
            return try {
                Paths.get(path)
                true
            } catch (e: InvalidPathException) {
                false
            }
        }
    }
}
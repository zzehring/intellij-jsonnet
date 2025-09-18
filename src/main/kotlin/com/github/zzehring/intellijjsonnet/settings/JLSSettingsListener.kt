package com.github.zzehring.intellijjsonnet.settings

import com.intellij.util.messages.Topic

/**
 * Project-level topic to notify listeners when Jsonnet LS settings change.
 */
fun interface JLSSettingsListener {

    fun onSettingsChanged(newState: JLSSettingsStateComponent.SettingsState)

    companion object {
        @JvmField
        val TOPIC: Topic<JLSSettingsListener> = Topic.create(
            "Jsonnet LS Settings Changed",
            JLSSettingsListener::class.java
        )
    }
}
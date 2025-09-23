package com.github.zzehring.intellijjsonnet.settings

import com.intellij.openapi.options.Configurable
import com.intellij.openapi.options.ConfigurableProvider
import com.intellij.openapi.project.Project

class JLSSettingsConfigurableProvider(private val project: Project) : ConfigurableProvider() {
    override fun createConfigurable(): Configurable {
        return JLSSettingsConfigurable(project)
    }
}

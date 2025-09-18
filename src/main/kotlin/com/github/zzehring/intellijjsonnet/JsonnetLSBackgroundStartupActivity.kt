package com.github.zzehring.intellijjsonnet

import com.github.zzehring.intellijjsonnet.settings.JLSSettingsListener
import com.intellij.openapi.project.Project
import com.intellij.openapi.startup.ProjectActivity

class JsonnetLSBackgroundStartupActivity : ProjectActivity {

    override suspend fun execute(project: Project) {
        val handler = JsonnetLSStartupHandler()
        handler.start(project)
        // Subscribe to settings changes to propagate them to the running LS
        val connection = project.messageBus.connect()
        connection.subscribe(
            JLSSettingsListener.TOPIC,
            JLSSettingsListener { state ->
                handler.onSettingsChanged(project, state.asSettings())
            }
        )
    }

}

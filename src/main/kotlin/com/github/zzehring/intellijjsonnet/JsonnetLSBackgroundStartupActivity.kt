package com.github.zzehring.intellijjsonnet

import com.intellij.openapi.project.Project
import com.intellij.openapi.startup.StartupActivity

class JsonnetLSBackgroundStartupActivity : StartupActivity.Background {
    override fun runActivity(project: Project) {
        val handler = JsonnetLSStartupHandler()
        handler.start()
    }

}
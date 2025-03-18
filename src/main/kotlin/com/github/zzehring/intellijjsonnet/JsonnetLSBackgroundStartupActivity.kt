package com.github.zzehring.intellijjsonnet

import com.intellij.openapi.project.Project
import com.intellij.openapi.startup.ProjectActivity
import com.intellij.openapi.startup.StartupActivity

class JsonnetLSBackgroundStartupActivity : ProjectActivity {
    override suspend fun execute(project: Project) {
        val handler = JsonnetLSStartupHandler()
        handler.start(project)
    }

}

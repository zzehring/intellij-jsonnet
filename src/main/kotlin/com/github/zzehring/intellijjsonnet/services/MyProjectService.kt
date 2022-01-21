package com.github.zzehring.intellijjsonnet.services

import com.intellij.openapi.project.Project
import com.github.zzehring.intellijjsonnet.MyBundle

class MyProjectService(project: Project) {

    init {
        println(MyBundle.message("projectService", project.name))
    }
}

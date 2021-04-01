package com.github.tiginamaria.kotlinanalyzerplugin.services

import com.github.tiginamaria.kotlinanalyzerplugin.MyBundle
import com.intellij.openapi.project.Project

class MyProjectService(project: Project) {

    init {
        println(MyBundle.message("projectService", project.name))
    }
}

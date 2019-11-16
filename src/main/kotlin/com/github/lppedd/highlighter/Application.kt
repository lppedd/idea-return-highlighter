package com.github.lppedd.highlighter

import com.intellij.codeInsight.daemon.DaemonCodeAnalyzer
import com.intellij.openapi.project.ProjectManager

/**
 * @author Edoardo Luppi
 */
object Application {
  fun refreshFiles() {
    ProjectManager.getInstance()
        .openProjects
        .map { DaemonCodeAnalyzer.getInstance(it) }
        .forEach { it.restart() }
  }
}

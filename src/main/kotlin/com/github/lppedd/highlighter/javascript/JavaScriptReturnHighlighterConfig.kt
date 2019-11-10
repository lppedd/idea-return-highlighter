package com.github.lppedd.highlighter.javascript

import com.github.lppedd.highlighter.Constants
import com.github.lppedd.highlighter.ReturnHighlightStrategy
import com.github.lppedd.highlighter.javascript.JavaScriptReturnHighlighterConfig.JavaScriptConfig
import com.intellij.codeInsight.daemon.DaemonCodeAnalyzer
import com.intellij.lang.javascript.psi.JSReturnStatement
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.components.PersistentStateComponent
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage
import com.intellij.openapi.project.ProjectManager

/**
 * @author Edoardo Luppi
 */
@State(
    name = "JavaScript",
    storages = [Storage(Constants.STORAGE_FILE)]
)
class JavaScriptReturnHighlighterConfig : PersistentStateComponent<JavaScriptConfig> {
  private var state = JavaScriptConfig()

  override fun loadState(state: JavaScriptConfig) {
    this.state = state
  }

  override fun getState(): JavaScriptConfig = state.copy()

  fun setState(state: JavaScriptConfig) {
    this.state = state
    refreshFiles()
  }

  fun getHighlightStrategy(): ReturnHighlightStrategy<JSReturnStatement> =
      if (state.isOnlyTopLevelReturns) JavaScriptTopLevelHighlightStrategy
      else JavaScriptAlwaysHighlightStrategy

  private fun refreshFiles() {
    ProjectManager.getInstance()
        .openProjects
        .map { DaemonCodeAnalyzer.getInstance(it) }
        .forEach { it.restart() }
  }

  companion object {
    val instance: JavaScriptReturnHighlighterConfig by lazy {
      ApplicationManager.getApplication().getComponent(JavaScriptReturnHighlighterConfig::class.java)
    }
  }

  data class JavaScriptConfig(
      var isOnlyTopLevelReturns: Boolean = false
  )
}

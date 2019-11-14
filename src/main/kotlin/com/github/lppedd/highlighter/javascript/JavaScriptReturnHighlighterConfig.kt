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
    storages = [Storage(Constants.ISTORAGE_FILE)]
)
class JavaScriptReturnHighlighterConfig : PersistentStateComponent<JavaScriptConfig> {
  private var state = JavaScriptConfig()
  private var highlightStrategy: ReturnHighlightStrategy<JSReturnStatement> = JavaScriptAlwaysHighlightStrategy

  override fun getState(): JavaScriptConfig = state.copy()
  override fun loadState(state: JavaScriptConfig) {
    this.state = state
    updateCurrentHighlightStrategy()
  }

  fun setState(state: JavaScriptConfig) {
    this.state = state
    updateCurrentHighlightStrategy()
    refreshFiles()
  }

  fun getHighlightStrategy() = highlightStrategy

  private fun updateCurrentHighlightStrategy() {
    var highlightStrategy: ReturnHighlightStrategy<JSReturnStatement> =
        JavaScriptAlwaysHighlightStrategy

    if (state.isOnlyTopLevelReturns) {
      highlightStrategy = JavaScriptTopLevelHighlightStrategy
    }

    if (state.isSkipSimpleGetters) {
      highlightStrategy = JavaScriptSimpleGetterHighlightStrategy(highlightStrategy)
    }

    this.highlightStrategy = highlightStrategy
  }

  private fun refreshFiles() {
    ProjectManager.getInstance()
        .openProjects
        .map { DaemonCodeAnalyzer.getInstance(it) }
        .forEach { it.restart() }
  }

  companion object {
    val INSTANCE: JavaScriptReturnHighlighterConfig by lazy {
      ApplicationManager.getApplication().getComponent(JavaScriptReturnHighlighterConfig::class.java)
    }
  }

  data class JavaScriptConfig(
      var isOnlyTopLevelReturns: Boolean = false,
      var isSkipSimpleGetters: Boolean = false
  )
}

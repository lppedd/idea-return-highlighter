package com.github.lppedd.highlighter.javascript

import com.github.lppedd.highlighter.Application
import com.github.lppedd.highlighter.Constants
import com.github.lppedd.highlighter.ReturnHighlightStrategy
import com.github.lppedd.highlighter.javascript.JavaScriptReturnHighlighterConfig.JavaScriptConfig
import com.intellij.lang.javascript.psi.JSReturnStatement
import com.intellij.openapi.components.PersistentStateComponent
import com.intellij.openapi.components.ServiceManager
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage

/**
 * @author Edoardo Luppi
 */
@State(
    name = "JavaScript",
    storages = [Storage(Constants.ISTORAGE_FILE)]
)
internal class JavaScriptReturnHighlighterConfig : PersistentStateComponent<JavaScriptConfig> {
  companion object {
    fun getInstance(): JavaScriptReturnHighlighterConfig =
      ServiceManager.getService(JavaScriptReturnHighlighterConfig::class.java)
  }

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
    Application.refreshFiles()
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

  data class JavaScriptConfig(
      var isOnlyTopLevelReturns: Boolean = false,
      var isSkipSimpleGetters: Boolean = false
  )
}

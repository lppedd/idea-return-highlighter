package com.github.lppedd.highlighter.python

import com.github.lppedd.highlighter.Application
import com.github.lppedd.highlighter.Constants
import com.github.lppedd.highlighter.ReturnHighlightStrategy
import com.github.lppedd.highlighter.python.PythonReturnHighlighterConfig.PythonConfig
import com.intellij.openapi.components.PersistentStateComponent
import com.intellij.openapi.components.ServiceManager
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage
import com.jetbrains.python.psi.PyReturnStatement

/**
 * @author Edoardo Luppi
 */
@State(
    name = "Python",
    storages = [Storage(Constants.ISTORAGE_FILE)]
)
internal class PythonReturnHighlighterConfig : PersistentStateComponent<PythonConfig> {
  companion object {
    fun getInstance(): PythonReturnHighlighterConfig =
      ServiceManager.getService(PythonReturnHighlighterConfig::class.java)
  }

  private var state = PythonConfig()
  private var highlightStrategy: ReturnHighlightStrategy<PyReturnStatement> = PythonAlwaysHighlightStrategy

  override fun getState(): PythonConfig = state.copy()
  override fun loadState(state: PythonConfig) {
    this.state = state
    updateCurrentHighlightStrategy()
  }

  fun setState(state: PythonConfig) {
    this.state = state
    updateCurrentHighlightStrategy()
    Application.refreshFiles()
  }

  fun getHighlightStrategy() = highlightStrategy

  private fun updateCurrentHighlightStrategy() {
    var highlightStrategy: ReturnHighlightStrategy<PyReturnStatement> =
      PythonAlwaysHighlightStrategy

    if (state.isOnlyTopLevelReturns) {
      highlightStrategy = PythonTopLevelHighlightStrategy
    }

    this.highlightStrategy = highlightStrategy
  }

  data class PythonConfig(var isOnlyTopLevelReturns: Boolean = false)
}

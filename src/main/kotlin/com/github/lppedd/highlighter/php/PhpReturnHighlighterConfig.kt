package com.github.lppedd.highlighter.php

import com.github.lppedd.highlighter.Application
import com.github.lppedd.highlighter.Constants
import com.github.lppedd.highlighter.ReturnHighlightStrategy
import com.github.lppedd.highlighter.php.PhpReturnHighlighterConfig.PhpConfig
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.components.PersistentStateComponent
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage
import com.jetbrains.php.lang.psi.elements.PhpReturn

/**
 * @author Edoardo Luppi
 */
@State(
    name = "PHP",
    storages = [Storage(Constants.ISTORAGE_FILE)]
)
class PhpReturnHighlighterConfig : PersistentStateComponent<PhpConfig> {
  private var state = PhpConfig()
  private var highlightStrategy: ReturnHighlightStrategy<PhpReturn> = PhpAlwaysHighlightStrategy

  override fun getState(): PhpConfig = state.copy()
  override fun loadState(state: PhpConfig) {
    this.state = state
    updateCurrentHighlightStrategy()
  }

  fun setState(state: PhpConfig) {
    this.state = state
    updateCurrentHighlightStrategy()
    Application.refreshFiles()
  }

  fun getHighlightStrategy() = highlightStrategy

  private fun updateCurrentHighlightStrategy() {
    var highlightStrategy: ReturnHighlightStrategy<PhpReturn> =
      PhpAlwaysHighlightStrategy

    if (state.isOnlyTopLevelReturns) {
      highlightStrategy = PhpTopLevelHighlightStrategy
    }

    this.highlightStrategy = highlightStrategy
  }

  companion object {
    val INSTANCE: PhpReturnHighlighterConfig by lazy {
      ApplicationManager.getApplication().getComponent(PhpReturnHighlighterConfig::class.java)
    }
  }

  data class PhpConfig(var isOnlyTopLevelReturns: Boolean = false)
}

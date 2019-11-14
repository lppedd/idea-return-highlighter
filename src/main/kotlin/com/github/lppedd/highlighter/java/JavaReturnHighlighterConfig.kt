package com.github.lppedd.highlighter.java

import com.github.lppedd.highlighter.Constants
import com.github.lppedd.highlighter.ReturnHighlightStrategy
import com.github.lppedd.highlighter.java.JavaReturnHighlighterConfig.JavaConfig
import com.intellij.codeInsight.daemon.DaemonCodeAnalyzer
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.components.PersistentStateComponent
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage
import com.intellij.openapi.project.ProjectManager
import com.intellij.psi.PsiKeyword

/**
 * @author Edoardo Luppi
 */
@State(
    name = "Java",
    storages = [Storage(Constants.ISTORAGE_FILE)]
)
class JavaReturnHighlighterConfig : PersistentStateComponent<JavaConfig> {
  private var state = JavaConfig()
  private var highlightStrategy: ReturnHighlightStrategy<PsiKeyword> = JavaAlwaysHighlightStrategy

  override fun getState(): JavaConfig = state.copy()
  override fun loadState(state: JavaConfig) {
    this.state = state
    updateCurrentHighlightStrategy()
  }

  fun setState(state: JavaConfig) {
    this.state = state
    updateCurrentHighlightStrategy()
    refreshFiles()
  }

  fun getHighlightStrategy() = highlightStrategy

  private fun updateCurrentHighlightStrategy() {
    var highlightStrategy: ReturnHighlightStrategy<PsiKeyword> = JavaAlwaysHighlightStrategy

    if (state.isOnlyTopLevelReturns) {
      highlightStrategy = JavaTopLevelHighlightStrategy
    }

    if (state.isSkipSimpleGetters) {
      highlightStrategy = JavaSimpleGetterHighlightStrategy(highlightStrategy)
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
    val INSTANCE: JavaReturnHighlighterConfig by lazy {
      ApplicationManager.getApplication().getComponent(JavaReturnHighlighterConfig::class.java)
    }
  }

  data class JavaConfig(
      var isOnlyTopLevelReturns: Boolean = false,
      var isSkipSimpleGetters: Boolean = false
  )
}

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
    storages = [Storage(Constants.STORAGE_FILE)]
)
class JavaReturnHighlighterConfig : PersistentStateComponent<JavaConfig> {
  private var state = JavaConfig()

  override fun loadState(state: JavaConfig) {
    this.state = state
  }

  override fun getState(): JavaConfig = state.copy()

  fun setState(state: JavaConfig) {
    this.state = state
    refreshFiles()
  }

  fun getHighlightStrategy(): ReturnHighlightStrategy<PsiKeyword> =
      if (state.isOnlyTopLevelReturns) JavaTopLevelHighlightStrategy
      else JavaAlwaysHighlightStrategy

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
      var isOnlyTopLevelReturns: Boolean = false
  )
}

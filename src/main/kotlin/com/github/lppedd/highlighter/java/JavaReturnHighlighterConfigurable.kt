package com.github.lppedd.highlighter.java

import com.github.lppedd.highlighter.Constants
import com.intellij.openapi.options.SearchableConfigurable
import javax.swing.JComponent

/**
 * @author Edoardo Luppi
 */
class JavaReturnHighlighterConfigurable : SearchableConfigurable {
  private val config = JavaReturnHighlighterConfig.INSTANCE
  private val gui = JavaReturnHighlighterConfigurableGui()

  override fun getId() = "preferences.${Constants.APP_NAME}.Java"
  override fun getDisplayName() = "Java"

  override fun apply() {
    config.state = config.state.copy(
        isOnlyTopLevelReturns = gui.isOnlyTopLevelReturns
    )
  }

  override fun reset() {
    gui.isOnlyTopLevelReturns = config.state.isOnlyTopLevelReturns
  }

  override fun isModified() =
      gui.isOnlyTopLevelReturns != config.state.isOnlyTopLevelReturns

  override fun createComponent(): JComponent? {
    gui.isOnlyTopLevelReturns = config.state.isOnlyTopLevelReturns
    return gui.rootPanel
  }
}

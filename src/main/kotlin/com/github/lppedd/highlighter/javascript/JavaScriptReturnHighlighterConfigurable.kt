package com.github.lppedd.highlighter.javascript

import com.github.lppedd.highlighter.Constants
import com.github.lppedd.highlighter.ReturnHighlighterBundle
import com.intellij.openapi.options.SearchableConfigurable
import javax.swing.JComponent

/**
 * @author Edoardo Luppi
 */
class JavaScriptReturnHighlighterConfigurable : SearchableConfigurable {
  private val config = JavaScriptReturnHighlighterConfig.INSTANCE
  private val gui = JavaScriptReturnHighlighterConfigurableGui()

  override fun getId() = "preferences.${Constants.IAPP_NAME}.JavaScript"
  override fun getDisplayName() = ReturnHighlighterBundle["rh.settings.javascript"]

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

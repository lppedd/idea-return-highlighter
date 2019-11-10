package com.github.lppedd.highlighter.javascript

import com.github.lppedd.highlighter.Constants
import com.intellij.openapi.options.SearchableConfigurable
import javax.swing.JComponent

/**
 * @author Edoardo Luppi
 */
class JavaScriptReturnHighlighterConfigurable : SearchableConfigurable {
  private val config = JavaScriptReturnHighlighterConfig.instance
  private val gui = JavaScriptReturnHighlighterConfigurableGui()

  override fun getId() = "preferences.${Constants.APP_NAME}.JavaScript"
  override fun getDisplayName() = "JavaScript &amp; TypeScript"

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

package com.github.lppedd.highlighter.java

import com.github.lppedd.highlighter.Constants
import com.github.lppedd.highlighter.ReturnHighlighterBundle
import com.intellij.openapi.options.SearchableConfigurable
import javax.swing.JComponent

/**
 * @author Edoardo Luppi
 */
class JavaReturnHighlighterConfigurable : SearchableConfigurable {
  private val config = JavaReturnHighlighterConfig.INSTANCE
  private val gui = JavaReturnHighlighterConfigurableGui(ReturnHighlighterBundle)

  override fun getId() = "preferences.${Constants.IAPP_NAME}.Java"
  override fun getDisplayName() = ReturnHighlighterBundle["rh.settings.java"]

  override fun apply() {
    config.state = config.state.copy(
        isOnlyTopLevelReturns = gui.isOnlyTopLevelReturns,
        isSkipSimpleGetters = gui.isSkipSimpleGetters
    )
  }

  override fun reset() {
    gui.isOnlyTopLevelReturns = config.state.isOnlyTopLevelReturns
    gui.isSkipSimpleGetters = config.state.isSkipSimpleGetters
  }

  override fun isModified() =
      gui.isOnlyTopLevelReturns != config.state.isOnlyTopLevelReturns
          || gui.isSkipSimpleGetters != config.state.isSkipSimpleGetters

  override fun createComponent(): JComponent? {
    gui.isOnlyTopLevelReturns = config.state.isOnlyTopLevelReturns
    gui.isSkipSimpleGetters = config.state.isSkipSimpleGetters
    return gui.rootPanel
  }
}

package com.github.lppedd.highlighter.php

import com.github.lppedd.highlighter.Constants
import com.github.lppedd.highlighter.ReturnHighlighterBundle
import com.intellij.openapi.options.SearchableConfigurable
import javax.swing.JComponent

/**
 * @author Edoardo Luppi
 */
internal class PhpReturnHighlighterConfigurable : SearchableConfigurable {
  private val config = PhpReturnHighlighterConfig.getInstance()
  private val gui = PhpReturnHighlighterConfigurableGui(ReturnHighlighterBundle)

  override fun getId() = "preferences.${Constants.IAPP_NAME}.php"
  override fun getDisplayName() = ReturnHighlighterBundle["rh.settings.php"]

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

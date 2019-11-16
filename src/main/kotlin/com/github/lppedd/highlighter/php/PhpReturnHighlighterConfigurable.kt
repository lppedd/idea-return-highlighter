package com.github.lppedd.highlighter.php

import com.github.lppedd.highlighter.Constants
import com.github.lppedd.highlighter.ReturnHighlighterBundle
import com.intellij.openapi.options.SearchableConfigurable
import javax.swing.JComponent

/**
 * @author Edoardo Luppi
 */
class PhpReturnHighlighterConfigurable : SearchableConfigurable {
  private val gui = PhpReturnHighlighterConfigurableGui(ReturnHighlighterBundle)

  override fun getId() = "preferences.${Constants.IAPP_NAME}.php"
  override fun getDisplayName() = ReturnHighlighterBundle["rh.settings.php"]
  override fun isModified() = false
  override fun apply() = Unit
  override fun createComponent(): JComponent? = gui.rootPanel
}

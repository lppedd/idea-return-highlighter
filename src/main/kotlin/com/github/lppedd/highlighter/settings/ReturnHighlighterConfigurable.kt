package com.github.lppedd.highlighter.settings

import com.github.lppedd.highlighter.Constants
import com.github.lppedd.highlighter.ReturnHighlighterBundle
import com.intellij.ide.DataManager
import com.intellij.openapi.options.Configurable
import com.intellij.openapi.options.ConfigurableEP
import com.intellij.openapi.options.SearchableConfigurable
import com.intellij.openapi.options.ex.Settings
import com.intellij.ui.components.JBLabel
import com.intellij.ui.components.labels.LinkLabel
import java.awt.Font
import javax.swing.border.EmptyBorder

/**
 * @author Edoardo Luppi
 */
internal class ReturnHighlighterConfigurable : SearchableConfigurable {
  private val noSupportedLanguage =
    JBLabel(ReturnHighlighterBundle["rh.settings.custom.global.noLang"]).apply {
      font = font.deriveFont(font.style or Font.BOLD)
    }

  private var gui = ReturnHighlighterConfigurableGui()

  init {
    Configurable.APPLICATION_CONFIGURABLE.extensionList
        .filter { it.id?.startsWith("preferences.${Constants.IAPP_NAME}.") ?: false }
        .map {
          val language = it.id.split(".")[2]
          val text = ReturnHighlighterBundle["rh.settings.$language"]
          LinkLabel.create(text, buildRunnable(it)).apply {
            alignmentX = 0f
            border = EmptyBorder(1, 1, 3, 1)
          }
        }
        .ifEmpty { listOf(noSupportedLanguage) }
        .forEach { gui.listPanel.add(it) }
  }

  override fun getId() = "preferences.${Constants.IAPP_NAME}"
  override fun getDisplayName() = ReturnHighlighterBundle["rh.app.presentableName"]
  override fun isModified() = false
  override fun apply() = Unit
  override fun createComponent() = gui.rootPanel

  private fun buildRunnable(configurableEP: ConfigurableEP<Configurable>): () -> Unit =
    {
      val settings = Settings.KEY.getData(DataManager.getInstance().getDataContext(gui.rootPanel))
      val configurable = settings?.find(configurableEP.id)
      settings?.select(configurable)
    }
}

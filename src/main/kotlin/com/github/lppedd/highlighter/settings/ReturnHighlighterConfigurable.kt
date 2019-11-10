package com.github.lppedd.highlighter.settings

import com.github.lppedd.highlighter.Constants
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
class ReturnHighlighterConfigurable : SearchableConfigurable {
  private val noSupportedLanguage =
      JBLabel("You have no supported languages enabled in your environment.").apply {
        font = font.deriveFont(font.style or Font.BOLD)
      }

  private var returnHighlighterGui: ReturnHighlighterConfigurableGui? = ReturnHighlighterConfigurableGui()
  private val rootPanel = returnHighlighterGui?.rootPanel

  init {
    val listPanel = returnHighlighterGui?.listPanel
    Configurable.APPLICATION_CONFIGURABLE.extensionList
        .filter { it.id?.startsWith("preferences.${Constants.APP_NAME}.") ?: false }
        .map {
          LinkLabel.create(it.displayName, buildRunnable(it)).apply {
            alignmentX = 0f
            border = EmptyBorder(1, 1, 3, 1)
          }
        }
        .ifEmpty { listOf(noSupportedLanguage) }
        .forEach { listPanel?.add(it) }
  }

  override fun getId() = "preferences.${Constants.APP_NAME}"
  override fun getDisplayName() = "Return Highlighter"
  override fun isModified() = false
  override fun apply() = Unit
  override fun createComponent() = rootPanel
  override fun disposeUIResources() {
    returnHighlighterGui = null
  }

  private fun buildRunnable(configurableEP: ConfigurableEP<Configurable>): () -> Unit =
      {
        val settings = Settings.KEY.getData(DataManager.getInstance().getDataContext(rootPanel))
        val configurable = settings?.find(configurableEP.id)
        settings?.select(configurable)
      }
}

package com.github.lppedd.highlighter.settings

import com.github.lppedd.highlighter.AbstractReturnAnnotator
import com.github.lppedd.highlighter.Icons
import com.intellij.openapi.fileTypes.PlainSyntaxHighlighter
import com.intellij.openapi.options.colors.AttributesDescriptor
import com.intellij.openapi.options.colors.ColorDescriptor
import com.intellij.openapi.options.colors.ColorSettingsPage
import com.intellij.psi.codeStyle.DisplayPriority
import com.intellij.psi.codeStyle.DisplayPrioritySortable

/**
 * @author Edoardo Luppi
 */
class ReturnHighlighterColorSettingsPage : ColorSettingsPage, DisplayPrioritySortable {
  override fun getDisplayName() = "Return Highlighter"
  override fun getIcon() = Icons.GUTTER_RETURN
  override fun getPriority() = DisplayPriority.COMMON_SETTINGS
  override fun getAttributeDescriptors() =
      arrayOf(AttributesDescriptor("Return keyword", AbstractReturnAnnotator.RETURN_KEYWORD))

  override fun getHighlighter() = PlainSyntaxHighlighter()
  override fun getAdditionalHighlightingTagToDescriptorMap() = null
  override fun getColorDescriptors(): Array<ColorDescriptor> = ColorDescriptor.EMPTY_ARRAY
  override fun getDemoText() = ""
}

package com.github.lppedd.highlighter.settings

import com.github.lppedd.highlighter.AbstractReturnAnnotator
import com.github.lppedd.highlighter.Icons
import com.github.lppedd.highlighter.ReturnHighlighterBundle
import com.intellij.openapi.fileTypes.PlainSyntaxHighlighter
import com.intellij.openapi.options.colors.AttributesDescriptor
import com.intellij.openapi.options.colors.ColorDescriptor
import com.intellij.openapi.options.colors.ColorSettingsPage
import com.intellij.psi.codeStyle.DisplayPriority
import com.intellij.psi.codeStyle.DisplayPrioritySortable

/**
 * @author Edoardo Luppi
 */
internal class ReturnHighlighterColorSettingsPage : ColorSettingsPage, DisplayPrioritySortable {
  override fun getDisplayName() = ReturnHighlighterBundle["rh.app.presentableName"]
  override fun getIcon() = Icons.GUTTER_RETURN
  override fun getPriority() = DisplayPriority.COMMON_SETTINGS
  override fun getAttributeDescriptors() =
    arrayOf(AttributesDescriptor(
        ReturnHighlighterBundle["rh.settings.colors.attr.retKey"],
        AbstractReturnAnnotator.TAK_RETURN
    ))

  override fun getHighlighter() = PlainSyntaxHighlighter()
  override fun getColorDescriptors(): Array<ColorDescriptor> = ColorDescriptor.EMPTY_ARRAY
  override fun getDemoText() = ReturnHighlighterBundle["rh.settings.colors.demoText"]

  override fun getAdditionalHighlightingTagToDescriptorMap() =
    mapOf(Pair("return", AbstractReturnAnnotator.TAK_RETURN))
}

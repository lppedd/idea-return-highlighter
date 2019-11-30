package com.github.lppedd.highlighter.php

import com.github.lppedd.highlighter.ReturnHighlighterBundle
import com.jetbrains.php.lang.psi.elements.PhpReturn
import com.github.lppedd.highlighter.AbstractReturnLineMarkerProvider as ARLMP

/**
 * @author Edoardo Luppi
 */
internal class PhpReturnLineMarkerProvider : ARLMP<PhpReturn>(PhpReturn::class.java) {
  private val config = PhpReturnHighlighterConfig.getInstance()

  override fun getName() = ReturnHighlighterBundle["rh.settings.php"]
  override fun isValidContext(psiElement: PhpReturn) =
    config.getHighlightStrategy().isValidContext(psiElement)
}

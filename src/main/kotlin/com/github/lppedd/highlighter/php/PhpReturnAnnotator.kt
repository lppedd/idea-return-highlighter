package com.github.lppedd.highlighter.php

import com.jetbrains.php.lang.psi.elements.PhpReturn
import com.github.lppedd.highlighter.AbstractReturnAnnotator as ARA

/**
 * @author Edoardo Luppi
 */
class PhpReturnAnnotator : ARA<PhpReturn>(PhpReturn::class.java) {
  private val config = PhpReturnHighlighterConfig.INSTANCE

  override fun isValidContext(psiElement: PhpReturn) =
    config.getHighlightStrategy().isValidContext(psiElement)
}

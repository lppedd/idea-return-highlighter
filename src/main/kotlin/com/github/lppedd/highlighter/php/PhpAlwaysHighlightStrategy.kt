package com.github.lppedd.highlighter.php

import com.github.lppedd.highlighter.ReturnHighlightStrategy
import com.jetbrains.php.lang.psi.elements.PhpReturn

/**
 * @author Edoardo Luppi
 */
internal object PhpAlwaysHighlightStrategy : ReturnHighlightStrategy<PhpReturn> {
  override fun isValidContext(psiElement: PhpReturn) = true
}

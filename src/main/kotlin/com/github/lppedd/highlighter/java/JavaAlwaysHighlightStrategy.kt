package com.github.lppedd.highlighter.java

import com.github.lppedd.highlighter.ReturnHighlightStrategy
import com.intellij.psi.PsiReturnStatement

/**
 * @author Edoardo Luppi
 */
object JavaAlwaysHighlightStrategy : ReturnHighlightStrategy<PsiReturnStatement> {
  override fun isValidContext(psiElement: PsiReturnStatement) = true
}

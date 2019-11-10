package com.github.lppedd.highlighter.java

import com.intellij.psi.PsiReturnStatement
import com.github.lppedd.highlighter.AbstractReturnLineMarkerProvider as ARLMP

/**
 * @author Edoardo Luppi
 */
class JavaReturnLineMarkerProvider : ARLMP<PsiReturnStatement>(PsiReturnStatement::class.java) {
  private val config = JavaReturnHighlighterConfig.INSTANCE

  override fun getName() = "Java"
  override fun isValidContext(psiElement: PsiReturnStatement) =
      config.getHighlightStrategy().isValidContext(psiElement)
}

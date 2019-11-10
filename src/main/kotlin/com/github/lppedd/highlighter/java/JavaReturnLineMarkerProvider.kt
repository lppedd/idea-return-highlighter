package com.github.lppedd.highlighter.java

import com.intellij.psi.PsiKeyword
import com.github.lppedd.highlighter.AbstractReturnLineMarkerProvider as ARLMP

/**
 * @author Edoardo Luppi
 */
class JavaReturnLineMarkerProvider : ARLMP<PsiKeyword>(PsiKeyword::class.java) {
  private val config = JavaReturnHighlighterConfig.INSTANCE

  override fun getName() = "Java"
  override fun isValidContext(psiElement: PsiKeyword) =
      config.getHighlightStrategy().isValidContext(psiElement)
}

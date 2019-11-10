package com.github.lppedd.highlighter.java

import com.github.lppedd.highlighter.ReturnHighlighterBundle
import com.intellij.psi.PsiKeyword
import com.github.lppedd.highlighter.AbstractReturnLineMarkerProvider as ARLMP

/**
 * @author Edoardo Luppi
 */
class JavaReturnLineMarkerProvider : ARLMP<PsiKeyword>(PsiKeyword::class.java) {
  private val config = JavaReturnHighlighterConfig.INSTANCE

  override fun getName() = ReturnHighlighterBundle["rh.settings.java"]
  override fun getPsiElement(psiElement: PsiKeyword) = psiElement
  override fun isValidContext(psiElement: PsiKeyword) =
      config.getHighlightStrategy().isValidContext(psiElement)
}

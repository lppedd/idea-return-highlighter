package com.github.lppedd.highlighter.java

import com.github.lppedd.highlighter.ReturnHighlightStrategy
import com.github.lppedd.highlighter.ReturnHighlightStrategy.PsiResult
import com.github.lppedd.highlighter.ReturnHighlightStrategy.PsiResult.*
import com.intellij.openapi.progress.ProgressManager
import com.intellij.psi.*
import com.intellij.psi.util.PsiTreeUtil

/**
 * @author Edoardo Luppi
 */
object JavaTopLevelHighlightStrategy : ReturnHighlightStrategy<PsiKeyword> {
  override fun isValidContext(psiElement: PsiKeyword): Boolean {
    // Note: it seems the Java Psi structure isn't quite right when errors are
    //  present. Thus, we need to intercept a lower-level PsiElement
    if (psiElement.text != PsiKeyword.RETURN) {
      return false
    }

    var psi: PsiElement? = psiElement

    while (psi != null) {
      ProgressManager.checkCanceled()
      psi = when (check(psi)) {
        VALID -> return true
        INVALID -> return false
        CONTINUE -> psi.parent
      }
    }

    return false
  }

  private fun check(psiElement: PsiElement): PsiResult =
      when (psiElement) {
        is PsiLambdaExpression -> checkLambdaExpression(psiElement)
        is PsiMethod -> VALID
        else -> CONTINUE
      }

  private fun checkLambdaExpression(psiElement: PsiLambdaExpression): PsiResult {
    // Lambda expressions are valid only if immediately assigned to a Field
    val psiField = PsiTreeUtil.getParentOfType(
        psiElement,
        PsiField::class.java,
        true,
        PsiLambdaExpression::class.java
    )

    return if (psiField is PsiField) VALID else INVALID
  }
}

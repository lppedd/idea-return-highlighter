package com.github.lppedd.highlighter.java

import com.github.lppedd.highlighter.ReturnHighlightStrategy.PsiResult
import com.github.lppedd.highlighter.ReturnHighlightStrategy.PsiResult.*
import com.github.lppedd.highlighter.TopLevelReturnHighlightStrategy
import com.intellij.psi.*
import com.intellij.psi.util.PsiTreeUtil

/**
 * @author Edoardo Luppi
 */
object JavaTopLevelHighlightStrategy : TopLevelReturnHighlightStrategy<PsiKeyword>() {
  override fun isValidContext(psiElement: PsiKeyword): Boolean {
    // Note: it seems the Java Psi structure isn't quite right when errors are
    //  present. Thus, we need to intercept a lower-level PsiElement
    return if (psiElement.text != PsiKeyword.RETURN) {
      false
    } else {
      super.isValidContext(psiElement)
    }
  }

  override fun check(psiElement: PsiElement): PsiResult =
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

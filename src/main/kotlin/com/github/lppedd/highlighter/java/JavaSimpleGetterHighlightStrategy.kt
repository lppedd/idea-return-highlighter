package com.github.lppedd.highlighter.java

import com.github.lppedd.highlighter.ReturnHighlightStrategy
import com.intellij.openapi.progress.ProgressManager
import com.intellij.psi.*
import com.intellij.psi.util.PsiTreeUtil

/**
 * @author Edoardo Luppi
 */
class JavaSimpleGetterHighlightStrategy(
    private val delegate: ReturnHighlightStrategy<PsiKeyword>
) : ReturnHighlightStrategy<PsiKeyword> {
  override fun isValidContext(psiElement: PsiKeyword): Boolean {
    if (!delegate.isValidContext(psiElement)) {
      return false
    }

    ProgressManager.checkCanceled()

    val psiCodeBlock = PsiTreeUtil.getParentOfType(
        psiElement,
        PsiReturnStatement::class.java,
        true
    )?.parent

    return if (psiCodeBlock is PsiCodeBlock && psiCodeBlock.parent is PsiMethod) {
      ProgressManager.checkCanceled()
      checkPsiCodeBlock(psiCodeBlock)
    } else {
      true
    }
  }

  private fun checkPsiCodeBlock(psiCodeBlock: PsiCodeBlock): Boolean {
    val nonEmptyStatements = psiCodeBlock.children.filter {
      it !is PsiJavaToken
      && it !is PsiEmptyStatement
      && it !is PsiWhiteSpace
    }

    return when {
      nonEmptyStatements.isEmpty() -> false
      nonEmptyStatements.size == 1 -> !containsOnlyReferences(nonEmptyStatements[0])
      else -> true
    }
  }

  private fun containsOnlyReferences(psiElement: PsiElement) =
    PsiTreeUtil.findChildOfAnyType(
        psiElement,
        PsiMethodCallExpression::class.java,
        PsiPolyadicExpression::class.java
    ) == null
}

package com.github.lppedd.highlighter.java

import com.github.lppedd.highlighter.ReturnHighlightStrategy
import com.github.lppedd.highlighter.ReturnHighlightStrategy.PsiResult
import com.github.lppedd.highlighter.ReturnHighlightStrategy.PsiResult.INVALID
import com.github.lppedd.highlighter.ReturnHighlightStrategy.PsiResult.VALID
import com.github.lppedd.highlighter.isChildOf
import com.intellij.psi.*

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

    val returnStatement = psiElement.isChildOf(PsiReturnStatement::class.java)
    val codeBlock = returnStatement?.parent

    return if (codeBlock is PsiCodeBlock && codeBlock.parent is PsiMethod) {
      checkPsiCodeBlock(codeBlock) == VALID
    } else {
      true
    }
  }

  private fun checkPsiCodeBlock(psiCodeBlock: PsiCodeBlock): PsiResult {
    for (value in psiCodeBlock.children) {
      if ((value is PsiJavaToken && value.tokenType == JavaTokenType.LBRACE)
          || value is PsiEmptyStatement
          || value is PsiWhiteSpace) {
        continue
      }

      if (value is PsiReturnStatement) return INVALID
      if (value !is PsiReturnStatement) return VALID
    }

    return VALID
  }
}

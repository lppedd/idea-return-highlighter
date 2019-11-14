package com.github.lppedd.highlighter.javascript

import com.github.lppedd.highlighter.ReturnHighlightStrategy
import com.github.lppedd.highlighter.ReturnHighlightStrategy.PsiResult
import com.github.lppedd.highlighter.ReturnHighlightStrategy.PsiResult.INVALID
import com.github.lppedd.highlighter.ReturnHighlightStrategy.PsiResult.VALID
import com.github.lppedd.highlighter.isChildOf
import com.intellij.lang.javascript.psi.JSBlockStatement
import com.intellij.lang.javascript.psi.JSEmptyStatement
import com.intellij.lang.javascript.psi.JSFunction
import com.intellij.lang.javascript.psi.JSReturnStatement
import com.intellij.psi.PsiWhiteSpace

/**
 * @author Edoardo Luppi
 */
class JavaScriptSimpleGetterHighlightStrategy(
    private val delegate: ReturnHighlightStrategy<JSReturnStatement>
) : ReturnHighlightStrategy<JSReturnStatement> {
  override fun isValidContext(psiElement: JSReturnStatement): Boolean {
    if (!delegate.isValidContext(psiElement)) {
      return false
    }

    val blockStatement = psiElement.isChildOf(JSBlockStatement::class.java)

    return if (blockStatement is JSBlockStatement && blockStatement.parent is JSFunction) {
      checkJSBlockStatement(blockStatement) == VALID
    } else {
      true
    }
  }

  private fun checkJSBlockStatement(jsBlockStatement: JSBlockStatement): PsiResult {
    for (value in jsBlockStatement.children) {
      if ((value == jsBlockStatement.firstChild)
          || value is JSEmptyStatement
          || value is PsiWhiteSpace) {
        continue
      }

      if (value is JSReturnStatement) return INVALID
      if (value !is JSReturnStatement) return VALID
    }

    return VALID
  }
}

package com.github.lppedd.highlighter.javascript

import com.github.lppedd.highlighter.ReturnHighlightStrategy
import com.intellij.lang.javascript.psi.*
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiWhiteSpace
import com.intellij.psi.impl.source.tree.LeafPsiElement
import com.intellij.psi.util.PsiTreeUtil

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

    val jsBlockStatement = PsiTreeUtil.getParentOfType(
        psiElement,
        JSBlockStatement::class.java,
        true
    )

    return if (jsBlockStatement is JSBlockStatement && jsBlockStatement.parent is JSFunction) {
      checkJSBlockStatement(jsBlockStatement, psiElement)
    } else {
      true
    }
  }

  private fun checkJSBlockStatement(
      jsBlockStatement: JSBlockStatement,
      jsReturnStatement: JSReturnStatement
  ): Boolean {
    // To be considered a simple getter, only a return statement must be present,
    // and that return statement must contain a simple expression (a reference or a literal)
    // Valid examples are:
    //    return a;
    //    return a.b;
    //    return a.b.c.d.e.f.length;
    val innerStatementsCount = jsBlockStatement.children.count {
      it !is LeafPsiElement &&
      it !is JSEmptyStatement &&
      it !is PsiWhiteSpace
    }

    if (innerStatementsCount != 1) {
      // There are other statements in addition to the return one
      return true
    }

    val nonEmptyExpressions = jsReturnStatement.children.filter {
      it !is JSEmptyStatement && it !is PsiWhiteSpace
    }

    return when {
      nonEmptyExpressions.isEmpty() -> false
      nonEmptyExpressions.size == 1 -> {
        val expression = nonEmptyExpressions[0]
        when {
          expression !is JSLiteralExpression &&
          expression !is JSQualifiedExpression -> true
          else -> !containsOnlyReferences(expression)
        }
      }
      else -> true
    }
  }

  private fun containsOnlyReferences(psiElement: PsiElement) =
    PsiTreeUtil.findChildOfAnyType(psiElement, JSCallExpression::class.java) == null
}

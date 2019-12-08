package com.github.lppedd.highlighter.python

import com.github.lppedd.highlighter.ReturnHighlightStrategy.PsiResult
import com.github.lppedd.highlighter.ReturnHighlightStrategy.PsiResult.*
import com.github.lppedd.highlighter.TopLevelReturnHighlightStrategy
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiWhiteSpace
import com.intellij.psi.util.PsiTreeUtil
import com.jetbrains.python.psi.*

/**
 * @author Edoardo Luppi
 */
internal object PythonTopLevelHighlightStrategy : TopLevelReturnHighlightStrategy<PyReturnStatement>() {
  override fun check(psiElement: PsiElement): PsiResult =
    when (psiElement) {
      is PyFunction -> checkFunction(psiElement)
      is PyReturnStatement -> checkReturnStatement(psiElement)
      else -> CONTINUE
    }

  /**
   * A return statement is valid inside a function which is a direct child
   * of a [PyFile] or [PyClass]. The class check is handled by [checkStatementList],
   * being that the PSI structure shows a class function is contained inside a [PyStatementList].
   */
  private fun checkFunction(pyFunction: PyFunction) =
    when (val parent = pyFunction.parent) {
      is PyFile -> VALID
      is PyStatementList -> checkStatementList(parent)
      else -> INVALID
    }

  private fun checkStatementList(pyStatementList: PyStatementList) =
    if (pyStatementList.parent is PyClass) VALID else INVALID

  /**
   * This check is meant to avoid highlighting the return keyword in case of grammar errors.
   * As of now it handles cases like:
   *
   * ```
   * f = lambda x: return x + 1
   * ```
   */
  private fun checkReturnStatement(pyReturnStatement: PyReturnStatement) =
    PsiTreeUtil.skipSiblingsBackward(pyReturnStatement, PsiWhiteSpace::class.java)?.let {
      if (PsiTreeUtil.getChildOfType(it, PyLambdaExpression::class.java) != null) {
        INVALID
      } else {
        CONTINUE
      }
    } ?: CONTINUE
}

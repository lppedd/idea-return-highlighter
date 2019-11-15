package com.github.lppedd.highlighter.javascript

import com.github.lppedd.highlighter.ReturnHighlightStrategy
import com.github.lppedd.highlighter.ReturnHighlightStrategy.PsiResult
import com.github.lppedd.highlighter.ReturnHighlightStrategy.PsiResult.*
import com.intellij.lang.javascript.psi.*
import com.intellij.lang.javascript.psi.ecmal4.JSClass
import com.intellij.lang.javascript.psi.ecmal4.JSQualifiedNamedElement
import com.intellij.psi.PsiElement
import com.intellij.psi.util.PsiTreeUtil

/**
 * @author Edoardo Luppi
 */
object JavaScriptTopLevelHighlightStrategy : ReturnHighlightStrategy<JSReturnStatement> {
  override fun isValidContext(psiElement: JSReturnStatement): Boolean {
    var psi: PsiElement? = psiElement

    while (psi != null) {
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
        is JSFunctionExpression -> checkJSFunctionExpression(psiElement)
        is JSFunction -> checkJSFunction(psiElement)
        else -> CONTINUE
      }

  private fun checkJSFunctionExpression(psiElement: JSFunctionExpression): PsiResult {
    // Function Expressions are valid only if immediately assigned
    // to a Class Field, when in Class scope
    val jsField = PsiTreeUtil.getParentOfType(
        psiElement,
        JSField::class.java,
        true,
        JSQualifiedNamedElement::class.java,
        JSFile::class.java
    )

    if (jsField is JSField) {
      return VALID
    }

    // Or when directly assigned to a Module Variable.
    // Note: we need to ensure the Variable is really top-level (Module),
    //  and this is done with the second isChildOf call
    val jsFile: JSFile? = PsiTreeUtil.getParentOfType(
        psiElement,
        JSVariable::class.java,
        true,
        JSQualifiedNamedElement::class.java
    )?.let {
      PsiTreeUtil.getParentOfType(
          it,
          JSFile::class.java,
          true,
          JSQualifiedNamedElement::class.java
      )
    }

    return if (jsFile is JSFile) VALID else INVALID
  }

  private fun checkJSFunction(psiElement: JSFunction): PsiResult {
    // A return statement is valid inside a Function which is a direct
    // child of a Class, or a Module (file).
    val parent: PsiElement? = psiElement.parent
    val isParentValid = parent is JSClass<*> || parent is JSFile
    return if (isParentValid) VALID else INVALID
  }
}

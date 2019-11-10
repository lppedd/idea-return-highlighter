package com.github.lppedd.highlighter.javascript

import com.github.lppedd.highlighter.ReturnHighlightStrategy
import com.github.lppedd.highlighter.ReturnHighlightStrategy.PsiResult
import com.github.lppedd.highlighter.isChildOf
import com.intellij.lang.javascript.psi.*
import com.intellij.lang.javascript.psi.ecmal4.JSClass
import com.intellij.lang.javascript.psi.ecmal4.JSQualifiedNamedElement
import com.intellij.psi.PsiElement

/**
 * @author Edoardo Luppi
 */
object JavaScriptTopLevelHighlightStrategy : ReturnHighlightStrategy<JSReturnStatement> {
  override fun isValidContext(psiElement: JSReturnStatement): Boolean {
    var psi: PsiElement? = psiElement

    while (psi != null) {
      psi = when (check(psi)) {
        PsiResult.VALID -> return true
        PsiResult.INVALID -> return false
        PsiResult.CONTINUE -> psi.parent
      }
    }

    return false
  }

  private fun check(psiElement: PsiElement): PsiResult =
      when (psiElement) {
        is JSFunctionExpression -> checkJSFunctionExpression(psiElement)
        is JSFunction -> checkJSFunction(psiElement)
        else -> PsiResult.CONTINUE
      }

  private fun checkJSFunctionExpression(psiElement: JSFunctionExpression): PsiResult {
    // Function Expressions are valid only if immediately assigned
    // to a Class Field, when in Class scope
    val jsField = psiElement.isChildOf(
        parentClass = JSField::class.java,
        stopClasses = *arrayOf(
            JSQualifiedNamedElement::class.java,
            JSFile::class.java
        )
    )

    if (jsField != null) {
      return PsiResult.VALID
    }

    // Or when directly assigned to a Module Variable
    var jsVariable = psiElement.isChildOf(
        parentClass = JSVariable::class.java,
        stopClasses = *arrayOf(JSQualifiedNamedElement::class.java)
    )

    // We need to ensure the Variable is really top-level (Module)
    if (jsVariable != null) {
      jsVariable = jsVariable.isChildOf(
          parentClass = JSFile::class.java,
          stopClasses = *arrayOf(JSQualifiedNamedElement::class.java)
      )
    }

    return if (jsVariable != null) {
      PsiResult.VALID
    } else {
      PsiResult.INVALID
    }
  }

  private fun checkJSFunction(psiElement: JSFunction): PsiResult {
    // A return statement is valid inside a Function which is a direct
    // child of a Class, or a Module (file).
    val parent = psiElement.parent

    return if (parent is JSClass<*> || parent is JSFile) {
      PsiResult.VALID
    } else {
      PsiResult.INVALID
    }
  }
}

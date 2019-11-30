package com.github.lppedd.highlighter.javascript

import com.github.lppedd.highlighter.ReturnHighlightStrategy.PsiResult
import com.github.lppedd.highlighter.ReturnHighlightStrategy.PsiResult.*
import com.github.lppedd.highlighter.TopLevelReturnHighlightStrategy
import com.intellij.lang.javascript.psi.*
import com.intellij.lang.javascript.psi.ecmal4.JSClass
import com.intellij.lang.javascript.psi.ecmal4.JSQualifiedNamedElement
import com.intellij.openapi.progress.ProgressManager
import com.intellij.psi.PsiElement
import com.intellij.psi.util.PsiTreeUtil

/**
 * @author Edoardo Luppi
 */
internal object JavaScriptTopLevelHighlightStrategy : TopLevelReturnHighlightStrategy<JSReturnStatement>() {
  override fun check(psiElement: PsiElement): PsiResult =
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

    ProgressManager.checkCanceled()

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

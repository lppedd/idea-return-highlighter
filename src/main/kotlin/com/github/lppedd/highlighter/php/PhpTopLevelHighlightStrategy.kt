package com.github.lppedd.highlighter.php

import com.github.lppedd.highlighter.ReturnHighlightStrategy.PsiResult
import com.github.lppedd.highlighter.ReturnHighlightStrategy.PsiResult.*
import com.github.lppedd.highlighter.TopLevelReturnHighlightStrategy
import com.intellij.openapi.progress.ProgressManager
import com.intellij.psi.PsiElement
import com.intellij.psi.util.PsiTreeUtil
import com.jetbrains.php.lang.parser.PhpElementTypes
import com.jetbrains.php.lang.psi.PhpFile
import com.jetbrains.php.lang.psi.elements.*
import com.jetbrains.php.lang.psi.elements.Function

/**
 * @author Edoardo Luppi
 */
internal object PhpTopLevelHighlightStrategy : TopLevelReturnHighlightStrategy<PhpReturn>() {
  override fun check(psiElement: PsiElement): PsiResult =
    when (psiElement) {
      is PhpExpression -> checkExpression(psiElement)
      is Function -> checkFunction(psiElement)
      else -> CONTINUE
    }

  private fun checkExpression(psiExpression: PhpExpression): PsiResult {
    if (!isClosure(psiExpression)) return VALID
    if (psiExpression.parent !is AssignmentExpression) return INVALID

    val psiMethod = PsiTreeUtil.getParentOfType(
        psiExpression,
        Method::class.java,
        true,
        Function::class.java
    )

    if (psiMethod?.name == "__construct") {
      return VALID
    }

    ProgressManager.checkCanceled()

    val phpFile = PsiTreeUtil.getParentOfType(
        psiExpression,
        GroupStatement::class.java,
        true,
        Function::class.java
    )?.parent

    return if (phpFile is PhpFile) VALID else INVALID
  }

  /**
   * A Function can be both a file Function and a class Method,
   * thus a return statement is valid inside a Function which is
   * a direct child of a Class, or a top-level statement group (<?php ... ?>).
   */
  private fun checkFunction(psiFunction: Function): PsiResult {
    if (isClosure(psiFunction.parent)) {
      return CONTINUE
    }

    ProgressManager.checkCanceled()

    val psiClass = PsiTreeUtil.getParentOfType(
        psiFunction,
        PhpClass::class.java,
        true,
        Function::class.java
    )

    if (psiClass != null) {
      return VALID
    }

    val parent: PsiElement? = psiFunction.parent
    val isParentValid = parent is GroupStatement && parent.parent is PhpFile
    return if (isParentValid) VALID else INVALID
  }

  private fun isClosure(psiElement: PsiElement) =
    psiElement is PhpExpression && psiElement.node.elementType == PhpElementTypes.CLOSURE
}

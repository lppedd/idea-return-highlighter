package com.github.lppedd.highlighter

import com.github.lppedd.highlighter.ReturnHighlightStrategy.PsiResult
import com.github.lppedd.highlighter.ReturnHighlightStrategy.PsiResult.*
import com.intellij.openapi.progress.ProgressManager
import com.intellij.psi.PsiElement

/**
 * @author Edoardo Luppi
 */
abstract class TopLevelReturnHighlightStrategy<T : PsiElement> : ReturnHighlightStrategy<T> {
  override fun isValidContext(psiElement: T): Boolean {
    var psi: PsiElement? = psiElement

    while (psi != null) {
      ProgressManager.checkCanceled()
      psi = when (check(psi)) {
        VALID -> return true
        INVALID -> return false
        CONTINUE -> psi.parent
      }
    }

    return false
  }

  protected abstract fun check(psiElement: PsiElement): PsiResult
}

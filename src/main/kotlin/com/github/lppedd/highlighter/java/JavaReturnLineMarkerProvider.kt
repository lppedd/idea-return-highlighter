package com.github.lppedd.highlighter.java

import com.intellij.psi.PsiReturnStatement
import com.github.lppedd.highlighter.AbstractReturnLineMarkerProvider as ARLMP

/**
 * @author Edoardo Luppi
 */
class JavaReturnLineMarkerProvider : ARLMP<PsiReturnStatement>(PsiReturnStatement::class.java) {
  override fun getName() = "Java"
}

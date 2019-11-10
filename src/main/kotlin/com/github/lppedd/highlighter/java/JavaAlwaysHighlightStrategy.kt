package com.github.lppedd.highlighter.java

import com.github.lppedd.highlighter.ReturnHighlightStrategy
import com.intellij.psi.PsiKeyword

/**
 * @author Edoardo Luppi
 */
object JavaAlwaysHighlightStrategy : ReturnHighlightStrategy<PsiKeyword> {
  // Note: it seems the Java Psi structure isn't quite right when errors are
  //  present. Thus, we need to intercept a lower-level PsiElement
  override fun isValidContext(psiElement: PsiKeyword) = "$psiElement" == "PsiKeyword:return"
}

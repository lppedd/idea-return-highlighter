package com.github.lppedd.highlighter.java

import com.github.lppedd.highlighter.ReturnHighlightStrategy
import com.intellij.psi.PsiKeyword

/**
 * @author Edoardo Luppi
 */
internal object JavaAlwaysHighlightStrategy : ReturnHighlightStrategy<PsiKeyword> {
  // Note: it seems the Java Psi structure isn't quite right when errors are
  //  present. Thus, we need to intercept a lower-level PsiElement
  override fun isValidContext(psiElement: PsiKeyword) =
    psiElement.text == PsiKeyword.RETURN
}

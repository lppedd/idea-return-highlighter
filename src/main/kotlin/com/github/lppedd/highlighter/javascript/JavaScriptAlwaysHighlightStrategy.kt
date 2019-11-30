package com.github.lppedd.highlighter.javascript

import com.github.lppedd.highlighter.ReturnHighlightStrategy
import com.intellij.lang.javascript.psi.JSReturnStatement

/**
 * @author Edoardo Luppi
 */
internal object JavaScriptAlwaysHighlightStrategy : ReturnHighlightStrategy<JSReturnStatement> {
  override fun isValidContext(psiElement: JSReturnStatement) = true
}

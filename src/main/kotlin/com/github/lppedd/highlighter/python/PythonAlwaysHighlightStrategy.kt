package com.github.lppedd.highlighter.python

import com.github.lppedd.highlighter.ReturnHighlightStrategy
import com.jetbrains.python.psi.PyReturnStatement

/**
 * @author Edoardo Luppi
 */
internal object PythonAlwaysHighlightStrategy : ReturnHighlightStrategy<PyReturnStatement> {
  override fun isValidContext(psiElement: PyReturnStatement) = true
}

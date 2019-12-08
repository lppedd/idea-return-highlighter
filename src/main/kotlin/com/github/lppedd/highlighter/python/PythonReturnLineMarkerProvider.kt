package com.github.lppedd.highlighter.python

import com.github.lppedd.highlighter.ReturnHighlighterBundle
import com.jetbrains.python.psi.PyReturnStatement
import com.github.lppedd.highlighter.AbstractReturnLineMarkerProvider as ARLMP

/**
 * @author Edoardo Luppi
 */
internal class PythonReturnLineMarkerProvider : ARLMP<PyReturnStatement>(PyReturnStatement::class.java) {
  private val config = PythonReturnHighlighterConfig.getInstance()

  override fun getName() = ReturnHighlighterBundle["rh.settings.python"]
  override fun isValidContext(psiElement: PyReturnStatement) =
    config.getHighlightStrategy().isValidContext(psiElement)
}

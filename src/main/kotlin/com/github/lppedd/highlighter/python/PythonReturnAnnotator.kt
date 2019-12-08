package com.github.lppedd.highlighter.python

import com.jetbrains.python.psi.PyReturnStatement
import com.github.lppedd.highlighter.AbstractReturnAnnotator as ARA

/**
 * @author Edoardo Luppi
 */
internal class PythonReturnAnnotator : ARA<PyReturnStatement>(PyReturnStatement::class.java) {
  private val config = PythonReturnHighlighterConfig.getInstance()

  override fun isValidContext(psiElement: PyReturnStatement) =
    config.getHighlightStrategy().isValidContext(psiElement)
}

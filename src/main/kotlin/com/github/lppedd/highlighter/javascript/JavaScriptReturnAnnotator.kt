package com.github.lppedd.highlighter.javascript

import com.intellij.lang.javascript.psi.JSReturnStatement
import com.github.lppedd.highlighter.AbstractReturnAnnotator as ARA

/**
 * @author Edoardo Luppi
 */
internal class JavaScriptReturnAnnotator : ARA<JSReturnStatement>(JSReturnStatement::class.java) {
  private val config = JavaScriptReturnHighlighterConfig.getInstance()

  override fun isValidContext(psiElement: JSReturnStatement) =
    config.getHighlightStrategy().isValidContext(psiElement)
}

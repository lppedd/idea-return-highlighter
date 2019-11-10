package com.github.lppedd.highlighter.javascript

import com.github.lppedd.highlighter.ReturnHighlighterBundle
import com.intellij.lang.javascript.psi.JSReturnStatement
import com.github.lppedd.highlighter.AbstractReturnLineMarkerProvider as ARLMP

/**
 * @author Edoardo Luppi
 */
class JavaScriptReturnLineMarkerProvider : ARLMP<JSReturnStatement>(JSReturnStatement::class.java) {
  private val config = JavaScriptReturnHighlighterConfig.INSTANCE

  override fun getName() = ReturnHighlighterBundle["rh.settings.javascript"]
  override fun isValidContext(psiElement: JSReturnStatement) =
      config.getHighlightStrategy().isValidContext(psiElement)
}

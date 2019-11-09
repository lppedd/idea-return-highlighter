package com.github.lppedd.highlighter.javascript

import com.intellij.lang.javascript.psi.JSReturnStatement
import com.github.lppedd.highlighter.AbstractReturnLineMarkerProvider as ARLMP

/**
 * @author Edoardo Luppi
 */
class JavaScriptReturnLineMarkerProvider : ARLMP<JSReturnStatement>(JSReturnStatement::class.java) {
	private val config = JavaScriptReturnHighlighterConfig.instance

	override fun getName() = "JavaScript/TypeScript"
	override fun isValidContext(psiElement: JSReturnStatement) =
			config.getHighlightStrategy().isValidContext(psiElement)
}

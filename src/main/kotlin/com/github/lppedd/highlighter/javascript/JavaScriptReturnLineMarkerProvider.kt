package com.github.lppedd.highlighter.javascript

import com.intellij.lang.javascript.psi.JSReturnStatement
import com.github.lppedd.highlighter.AbstractReturnLineMarkerProvider as ARLMP

/**
 * @author Edoardo Luppi
 */
class JavaScriptReturnLineMarkerProvider : ARLMP<JSReturnStatement>(JSReturnStatement::class.java) {
	override fun getName() = "JavaScript/TypeScript"
}

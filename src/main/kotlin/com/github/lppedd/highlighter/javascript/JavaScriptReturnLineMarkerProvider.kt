package com.github.lppedd.highlighter.javascript

import com.github.lppedd.highlighter.Icons
import com.github.lppedd.highlighter.createLineMarkerInfo
import com.intellij.codeInsight.daemon.LineMarkerProviderDescriptor
import com.intellij.lang.javascript.psi.JSReturnStatement
import com.intellij.psi.PsiElement

/**
 * @author Edoardo Luppi
 */
class JavaScriptReturnLineMarkerProvider : LineMarkerProviderDescriptor() {
	override fun getName() = "JavaScript/TypeScript"
	override fun getIcon() = Icons.GUTTER_RETURN
	override fun getLineMarkerInfo(element: PsiElement) =
			if (element is JSReturnStatement) createLineMarkerInfo(element.firstChild)
			else null
}

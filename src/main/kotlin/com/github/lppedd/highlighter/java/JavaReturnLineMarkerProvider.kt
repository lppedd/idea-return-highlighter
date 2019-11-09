package com.github.lppedd.highlighter.java

import com.github.lppedd.highlighter.Icons
import com.github.lppedd.highlighter.createLineMarkerInfo
import com.intellij.codeInsight.daemon.LineMarkerInfo
import com.intellij.codeInsight.daemon.LineMarkerProviderDescriptor
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiReturnStatement

/**
 * @author Edoardo Luppi
 */
class JavaReturnLineMarkerProvider : LineMarkerProviderDescriptor() {
	override fun getName() = "Java"
	override fun getIcon() = Icons.GUTTER_RETURN
	override fun getLineMarkerInfo(element: PsiElement): LineMarkerInfo<PsiElement>? =
			if (element is PsiReturnStatement) createLineMarkerInfo(element.firstChild)
			else null
}

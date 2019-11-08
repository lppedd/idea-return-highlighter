package com.github.lppedd.highlighter.javascript

import com.github.lppedd.highlighter.createLineMarkerInfo
import com.intellij.codeInsight.daemon.LineMarkerInfo
import com.intellij.codeInsight.daemon.LineMarkerProvider
import com.intellij.lang.javascript.psi.JSReturnStatement
import com.intellij.psi.PsiElement

/**
 * @author Edoardo Luppi
 */
class JavaScriptReturnLineMarkerProvider : LineMarkerProvider {
    override fun getLineMarkerInfo(element: PsiElement): LineMarkerInfo<PsiElement>? =
            if (element is JSReturnStatement) createLineMarkerInfo(element.firstChild)
            else null
}

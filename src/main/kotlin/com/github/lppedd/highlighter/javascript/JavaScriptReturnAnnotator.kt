package com.github.lppedd.highlighter.javascript

import com.github.lppedd.highlighter.BaseReturnAnnotator
import com.intellij.lang.annotation.AnnotationHolder
import com.intellij.lang.javascript.psi.JSReturnStatement
import com.intellij.psi.PsiElement

/**
 * @author Edoardo Luppi
 */
class JavaScriptReturnAnnotator : BaseReturnAnnotator() {
    override fun annotate(element: PsiElement, holder: AnnotationHolder) {
        if (element is JSReturnStatement) {
            holder.createInfoAnnotation(element.firstChild, null).also {
                it.enforcedTextAttributes = getTextAttributes()
            }
        }
    }
}

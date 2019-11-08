package com.github.lppedd.highlighter.java

import com.github.lppedd.highlighter.BaseReturnAnnotator
import com.intellij.lang.annotation.AnnotationHolder
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiReturnStatement

/**
 * @author Edoardo Luppi
 */
class JavaReturnAnnotator : BaseReturnAnnotator() {
    override fun annotate(element: PsiElement, holder: AnnotationHolder) {
        if (element is PsiReturnStatement) {
            holder.createInfoAnnotation(element.firstChild, null).also {
                it.enforcedTextAttributes = getTextAttributes()
            }
        }
    }
}

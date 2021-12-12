package com.github.lppedd.highlighter

import com.intellij.lang.annotation.AnnotationHolder
import com.intellij.lang.annotation.Annotator
import com.intellij.lang.annotation.HighlightSeverity
import com.intellij.openapi.editor.colors.EditorColorsManager
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.psi.PsiElement

/**
 * @author Edoardo Luppi
 */
abstract class AbstractReturnAnnotator<in T : PsiElement>(private val klass: Class<T>) : Annotator {
  companion object {
    val TAK_RETURN: TextAttributesKey = TextAttributesKey.createTextAttributesKey(
        "RH_RETURN_KEYWORD",
        TextAttributesKey.createTextAttributesKey("Return Highlighter defaults")
    )
  }

  private val returnKeywordAttributes = EditorColorsManager.getInstance()
      .globalScheme
      .getAttributes(TAK_RETURN)

  final override fun annotate(psiElement: PsiElement, holder: AnnotationHolder) {
    @Suppress("UNCHECKED_CAST")
    if (!returnKeywordAttributes.isEmpty
        && klass.isAssignableFrom(psiElement::class.java)
        && isValidContext(psiElement as T)
    ) {
      getPsiElement(psiElement)?.also {
        holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
            .enforcedTextAttributes(returnKeywordAttributes)
            .range(it)
            .create()
      }
    }
  }

  protected open fun getPsiElement(psiElement: T): PsiElement? = psiElement.firstChild ?: psiElement
  protected open fun isValidContext(psiElement: T): Boolean = true
}

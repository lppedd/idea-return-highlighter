package com.github.lppedd.highlighter

import com.intellij.lang.annotation.AnnotationHolder
import com.intellij.lang.annotation.Annotator
import com.intellij.openapi.editor.colors.EditorColorsManager
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.psi.PsiElement

/**
 * @author Edoardo Luppi
 */
abstract class AbstractReturnAnnotator<T : PsiElement>(private val klass: Class<T>) : Annotator {
  companion object {
    val RETURN_KEYWORD = TextAttributesKey.createTextAttributesKey("RH_RETURN_KEYWORD")
  }

  private val returnKeywordAttributes = EditorColorsManager.getInstance()
      .globalScheme
      .getAttributes(RETURN_KEYWORD)

  final override fun annotate(psiElement: PsiElement, holder: AnnotationHolder) {
    @Suppress("UNCHECKED_CAST")
    if (klass.isAssignableFrom(psiElement::class.java) && isValidContext(psiElement as T)) {
      getPsiElement(psiElement)?.also {
        holder.createInfoAnnotation(it, null).run {
          enforcedTextAttributes = returnKeywordAttributes
        }
      }
    }
  }

  protected open fun getPsiElement(psiElement: T): PsiElement? = psiElement.firstChild ?: psiElement
  protected open fun isValidContext(psiElement: T) = true
}

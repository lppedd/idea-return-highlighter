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

	@Suppress("UNCHECKED_CAST")
	final override fun annotate(element: PsiElement, holder: AnnotationHolder) {
		if (!klass.isAssignableFrom(element::class.java)) return

		getExactPsiElement(element as T)?.also {
			holder.createInfoAnnotation(it, null).run {
				enforcedTextAttributes = returnKeywordAttributes
			}
		}
	}

	protected open fun getExactPsiElement(psiReturnStatement: T): PsiElement? =
			psiReturnStatement.firstChild
}

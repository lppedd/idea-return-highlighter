package com.github.lppedd.highlighter

import com.intellij.codeInsight.daemon.LineMarkerInfo
import com.intellij.codeInsight.daemon.LineMarkerProviderDescriptor
import com.intellij.openapi.editor.markup.GutterIconRenderer
import com.intellij.psi.PsiElement
import com.intellij.util.PsiNavigateUtil

/**
 * @author Edoardo Luppi
 */
abstract class AbstractReturnLineMarkerProvider<T : PsiElement>(private val klass: Class<T>) : LineMarkerProviderDescriptor() {
	override fun getIcon() = Icons.GUTTER_RETURN

	@Suppress("UNCHECKED_CAST")
	final override fun getLineMarkerInfo(element: PsiElement) =
			if (klass.isAssignableFrom(element::class.java))
				createLineMarkerInfo(getExactPsiElement(element as T))
			else null

	protected open fun getExactPsiElement(psiReturnStatement: T): PsiElement? =
			psiReturnStatement.firstChild

	private fun createLineMarkerInfo(psiElement: PsiElement?): LineMarkerInfo<PsiElement>? =
			if (psiElement == null) null
			else LineMarkerInfo(
					psiElement,
					psiElement.textRange,
					Icons.GUTTER_RETURN,
					{ "Return statement" },
					{ _, elt -> PsiNavigateUtil.navigate(elt) },
					GutterIconRenderer.Alignment.LEFT
			)
}

package com.github.lppedd.highlighter

import com.intellij.codeInsight.daemon.LineMarkerInfo
import com.intellij.codeInsight.daemon.LineMarkerProviderDescriptor
import com.intellij.openapi.editor.markup.GutterIconRenderer
import com.intellij.psi.PsiElement
import com.intellij.util.PsiNavigateUtil
import javax.swing.Icon

/**
 * @author Edoardo Luppi
 */
abstract class AbstractReturnLineMarkerProvider<in T : PsiElement>(private val klass: Class<T>) : LineMarkerProviderDescriptor() {
  final override fun getIcon(): Icon = Icons.GUTTER_RETURN
  final override fun getLineMarkerInfo(element: PsiElement): LineMarkerInfo<PsiElement>? {
    @Suppress("UNCHECKED_CAST")
    if (klass.isAssignableFrom(element::class.java) && isValidContext(element as T)) {
      return createLineMarkerInfo(getPsiElement(element))
    }

    return null
  }

  protected open fun getPsiElement(psiElement: T): PsiElement? = psiElement.firstChild ?: psiElement
  protected open fun isValidContext(psiElement: T): Boolean = true

  private fun createLineMarkerInfo(psiElement: PsiElement?): LineMarkerInfo<PsiElement>? =
    if (psiElement == null) null
    else LineMarkerInfo(
        psiElement,
        psiElement.textRange,
        Icons.GUTTER_RETURN,
        { ReturnHighlighterBundle["rh.gutter.text"] },
        { _, elt -> PsiNavigateUtil.navigate(elt) },
        GutterIconRenderer.Alignment.LEFT
    )
}

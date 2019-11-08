package com.github.lppedd.highlighter

import com.intellij.codeInsight.daemon.LineMarkerInfo
import com.intellij.openapi.editor.markup.GutterIconRenderer
import com.intellij.psi.PsiElement
import com.intellij.util.PsiNavigateUtil

internal fun createLineMarkerInfo(psiElement: PsiElement): LineMarkerInfo<PsiElement> =
        LineMarkerInfo(
                psiElement,
                psiElement.textRange,
                Icons.GUTTER_RETURN,
                { "Return statement" },
                { _, elt -> PsiNavigateUtil.navigate(elt) },
                GutterIconRenderer.Alignment.LEFT
        )

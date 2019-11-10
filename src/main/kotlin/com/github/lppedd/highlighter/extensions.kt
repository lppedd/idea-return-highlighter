package com.github.lppedd.highlighter

import com.intellij.psi.PsiElement

fun Array<out Class<out PsiElement>>.containsClass(psiClass: Class<out PsiElement>): Boolean {
  for (i in 0 until size) {
    if (get(i).isAssignableFrom(psiClass)) {
      return true
    }
  }

  return false
}

fun PsiElement.isChildOf(
    parentClass: Class<out PsiElement>,
    vararg stopClasses: Class<out PsiElement>
): PsiElement? {
  var psi: PsiElement? = parent

  while (psi != null) {
    val psiClass = psi::class.java

    when {
      parentClass.isAssignableFrom(psiClass) -> return psi
      stopClasses.containsClass(psiClass) -> return null
      else -> psi = psi.parent
    }
  }

  return null
}

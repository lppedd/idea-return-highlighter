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

fun <T : PsiElement> PsiElement.isChildOf(
    parentClass: Class<out T>,
    vararg stopClasses: Class<out T>
): T? {
  var psi: PsiElement? = parent

  while (psi != null) {
    val psiClass = psi::class.java

    @Suppress("UNCHECKED_CAST")
    when {
      parentClass.isAssignableFrom(psiClass) -> return psi as T?
      stopClasses.containsClass(psiClass) -> return null
      else -> psi = psi.parent
    }
  }

  return null
}

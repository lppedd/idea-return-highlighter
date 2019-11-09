package com.github.lppedd.highlighter.javascript

import com.github.lppedd.highlighter.ReturnHighlightStrategy
import com.github.lppedd.highlighter.ReturnHighlightStrategy.PsiResult
import com.intellij.lang.javascript.psi.*
import com.intellij.lang.javascript.psi.ecmal4.JSClass
import com.intellij.psi.PsiElement

/**
 * @author Edoardo Luppi
 */
object JavaScriptTopLevelHighlightStrategy : ReturnHighlightStrategy<JSReturnStatement> {
	override fun isValidContext(psiElement: JSReturnStatement): Boolean {
		var element: PsiElement? = psiElement

		while (element != null) {
			element = when (check(element)) {
				PsiResult.VALID -> return true
				PsiResult.INVALID -> return false
				PsiResult.CONTINUE -> element.parent
			}
		}

		return false
	}

	private fun check(psiElement: PsiElement) =
			when (psiElement) {
				is JSFunctionExpression -> checkJSFunctionExpression(psiElement)
				is JSFunction -> checkJSFunction(psiElement)
				else -> PsiResult.CONTINUE
			}

	private fun checkJSFunctionExpression(psiElement: JSFunctionExpression): PsiResult {
		var element: PsiElement? = psiElement
		var valid = false

		while (element != null) {
			if (element is JSClass<*> || element is JSFile) {
				break
			}

			if (element !is JSFunctionExpression && element is JSFunction) {
				break
			}

			if (element is JSFunctionExpression) {
				valid = element.children.filterIsInstance(JSBlockStatement::class.java).isEmpty()
			}

			element = element.parent
		}

		return if (valid) PsiResult.VALID else PsiResult.INVALID
	}

	private fun checkJSFunction(psiElement: JSFunction) =
			if (psiElement.parent is JSClass<*> || psiElement.parent is JSFile) {
				PsiResult.VALID
			} else {
				PsiResult.INVALID
			}
}

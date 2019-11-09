package com.github.lppedd.highlighter.java

import com.intellij.psi.PsiReturnStatement
import com.github.lppedd.highlighter.AbstractReturnAnnotator as ARA

/**
 * @author Edoardo Luppi
 */
class JavaReturnAnnotator : ARA<PsiReturnStatement>(PsiReturnStatement::class.java)

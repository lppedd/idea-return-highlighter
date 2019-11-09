package com.github.lppedd.highlighter.java

import com.github.lppedd.highlighter.AbstractReturnAnnotator
import com.intellij.psi.PsiReturnStatement

/**
 * @author Edoardo Luppi
 */
class JavaReturnAnnotator : AbstractReturnAnnotator<PsiReturnStatement>(PsiReturnStatement::class.java)

package com.github.lppedd.highlighter.javascript

import com.intellij.lang.javascript.psi.JSReturnStatement
import com.github.lppedd.highlighter.AbstractReturnAnnotator as ARA

/**
 * @author Edoardo Luppi
 */
class JavaScriptReturnAnnotator : ARA<JSReturnStatement>(JSReturnStatement::class.java)

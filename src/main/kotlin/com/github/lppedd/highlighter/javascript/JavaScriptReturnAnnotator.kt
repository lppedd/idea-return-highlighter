package com.github.lppedd.highlighter.javascript

import com.github.lppedd.highlighter.AbstractReturnAnnotator
import com.intellij.lang.javascript.psi.JSReturnStatement

/**
 * @author Edoardo Luppi
 */
class JavaScriptReturnAnnotator : AbstractReturnAnnotator<JSReturnStatement>(JSReturnStatement::class.java)

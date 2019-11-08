package com.github.lppedd.highlighter

import com.intellij.lang.annotation.Annotator
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.editor.colors.EditorColorsListener
import com.intellij.openapi.editor.colors.EditorColorsManager
import com.intellij.openapi.editor.markup.TextAttributes
import java.awt.Color
import java.awt.Font
import java.util.concurrent.atomic.AtomicBoolean

/**
 * @author Edoardo Luppi
 */
abstract class BaseReturnAnnotator : Annotator {
    private val editorColorsManager = EditorColorsManager.getInstance()
    private val checkScheme = AtomicBoolean(true)
    private val bgLight = Color.decode("#E8E8E8")
    private val bgDark = Color.decode("#344134")
    private val textAttributes = TextAttributes(
            null,
            bgLight,
            null,
            null,
            Font.PLAIN
    )

    init {
        ApplicationManager.getApplication()
                .messageBus
                .connect()
                .subscribe(
                        EditorColorsManager.TOPIC,
                        EditorColorsListener { checkScheme.compareAndSet(false, true) }
                )
    }

    protected fun getTextAttributes(): TextAttributes {
        if (checkScheme.compareAndSet(true, false)) {
            val bgColor = if (editorColorsManager.isDarkEditor) bgDark else bgLight
            textAttributes.backgroundColor = bgColor
        }

        return textAttributes;
    }
}

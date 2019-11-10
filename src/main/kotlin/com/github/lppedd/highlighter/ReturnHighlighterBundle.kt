package com.github.lppedd.highlighter

import com.intellij.CommonBundle
import org.jetbrains.annotations.NonNls
import org.jetbrains.annotations.PropertyKey
import java.lang.ref.Reference
import java.lang.ref.SoftReference
import java.util.*

/**
 * @author Edoardo Luppi
 */
object ReturnHighlighterBundle {
  private const val BUNDLE = "messages.ReturnHighlighterBundle"
  private var ourBundle: Reference<ResourceBundle>? = null

  operator fun get(@PropertyKey(resourceBundle = BUNDLE) key: String, vararg params: Any): String {
    val bundle = getBundle() ?: return ""
    return CommonBundle.message(bundle, key, *params)
  }

  @NonNls
  private fun getBundle(): ResourceBundle? {
    var bundle = com.intellij.reference.SoftReference.dereference(ourBundle)

    if (bundle == null) {
      bundle = ResourceBundle.getBundle(BUNDLE)
      ourBundle = SoftReference(bundle)
    }

    return bundle
  }
}

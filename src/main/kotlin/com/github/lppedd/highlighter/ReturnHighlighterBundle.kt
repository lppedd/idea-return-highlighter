package com.github.lppedd.highlighter

import com.intellij.BundleBase
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
  private var bundle: Reference<ResourceBundle>? = null

  operator fun get(@PropertyKey(resourceBundle = BUNDLE) key: String, vararg params: Any): String =
    BundleBase.message(getBundle(), key, *params)

  @NonNls
  private fun getBundle(): ResourceBundle {
    var derefBundle = com.intellij.reference.SoftReference.dereference(bundle)

    if (derefBundle == null) {
      derefBundle = ResourceBundle.getBundle(BUNDLE)
      bundle = SoftReference(derefBundle)
      return derefBundle
    }

    return derefBundle
  }
}

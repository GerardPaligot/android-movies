package com.paligot.movies

import android.content.Context
import android.content.Intent

/**
 * Helpers to start activities in a modularized world.
 */

/**
 * Create an Intent with [Intent.ACTION_VIEW] to an [AddressableActivity].
 */
fun intentTo(context: Context, addressableActivity: AddressableActivity): Intent {
  return Intent(Intent.ACTION_VIEW).setClassName(
    context,
    addressableActivity.className
  )
}

/**
 * An [android.app.Activity] that can be addressed by an intent.
 */
interface AddressableActivity {
  val packageName: String
  val className: String
}

object Activities {
  /**
   * UserActivity
   */
  object User : AddressableActivity {
    override val packageName = "com.paligot.user"
    override val className = "$packageName.main.MainUserActivity"
  }
}
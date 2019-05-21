package com.paligot.shared.extensions

import java.text.SimpleDateFormat
import java.util.*

operator fun Date.plus(days: Int): Date {
  val calendar = Calendar.getInstance()
  calendar.add(Calendar.DAY_OF_YEAR, days)
  return calendar.time
}

fun Date.toString(format: String, locale: Locale = Locale.getDefault()): String {
  val formatter = SimpleDateFormat(format, locale)
  return formatter.format(this)
}
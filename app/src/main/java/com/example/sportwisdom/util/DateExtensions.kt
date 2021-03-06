package com.example.sportwisdom.util

import java.text.SimpleDateFormat
import java.util.*

/** Extension needed to convert a timestamp string into a Date object and applying the the time zone */
fun String.toDate(
  dateFormat: String = "yyyy-MM-dd'T'HH:mm:ss",
  timeZone: TimeZone = TimeZone.getTimeZone("UTC")
): Date? {
  val parser = SimpleDateFormat(dateFormat, Locale.getDefault())
  parser.timeZone = timeZone
  return parser.parse(this)
}

/** Extension to format a date object into Date, Time or Date and Time */
fun Date.formatTo(
  dateFormat: String,
  timeZone: TimeZone = TimeZone.getDefault()
): String {
  val formatter = SimpleDateFormat(dateFormat, Locale.getDefault())
  formatter.timeZone = timeZone
  return formatter.format(this)
}
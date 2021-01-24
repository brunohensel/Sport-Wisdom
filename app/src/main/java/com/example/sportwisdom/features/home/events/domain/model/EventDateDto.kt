package com.example.sportwisdom.features.home.events.domain.model

data class EventDateDto(
  val year: Int = 0,
  val month: Int = 0,
  val day: Int = 0,
  val hour: Int? = null,
  val minute: Int? = null
) {

  fun isTimeNotNull(): Boolean {
    return hour != null && minute != null
  }
}
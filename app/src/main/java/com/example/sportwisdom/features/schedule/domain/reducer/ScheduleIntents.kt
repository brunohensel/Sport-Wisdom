package com.example.sportwisdom.features.schedule.domain.reducer

sealed class ScheduleIntents {
  object FetchCachedEvents : ScheduleIntents()
  object DeleteAllEvents : ScheduleIntents()
  data class DeleteEvent(val eventId: String) : ScheduleIntents()
}
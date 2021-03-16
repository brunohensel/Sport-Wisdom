package com.example.sportwisdom.domain.reducer.schedule

sealed class ScheduleIntents {
  object FetchCachedEvents : ScheduleIntents()
  object DeleteAllEvents : ScheduleIntents()
  data class DeleteEvent(val eventId: String) : ScheduleIntents()
}
package com.example.sportwisdom.features.schedule.domain.state

import com.example.sportwisdom.features.home.events.domain.model.EventDto

data class ScheduleState(
  val scheduleModel: List<EventDto>,
  val syncState: ScheduleSyncState
)

sealed class ScheduleSyncState {
  object Loading : ScheduleSyncState()
  object Content : ScheduleSyncState()
  object Empty : ScheduleSyncState()
  data class Message(val msg: String?) : ScheduleSyncState()
}
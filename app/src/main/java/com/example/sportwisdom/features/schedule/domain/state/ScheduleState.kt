package com.example.sportwisdom.features.schedule.domain.state

import com.example.sportwisdom.features.home.events.domain.model.EventDto
import kotlinx.coroutines.flow.Flow

data class ScheduleState(
  val scheduleModel: Flow<List<EventDto>>,
  val syncState: ScheduleSyncState
)

sealed class ScheduleSyncState {
  object Loading : ScheduleSyncState()
  object Content : ScheduleSyncState()
  object SideEffect : ScheduleSyncState()
  object Empty : ScheduleSyncState()
  data class Message(val msg: String?) : ScheduleSyncState()
}
package com.example.sportwisdom.domain.reducer.schedule.state

import kotlinx.coroutines.flow.Flow

data class ScheduleState(
  val scheduleModel: Flow<List<com.example.sportwisdom.domain.model.EventDto>>,
  val syncState: ScheduleSyncState
)

sealed class ScheduleSyncState {
  object Loading : ScheduleSyncState()
  object Content : ScheduleSyncState()
  object SideEffect : ScheduleSyncState()
  object Empty : ScheduleSyncState()
  data class Message(val msg: String?) : ScheduleSyncState()
}
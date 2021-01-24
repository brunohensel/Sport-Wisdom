package com.example.sportwisdom.features.search.domain.state

import com.example.sportwisdom.features.schedule.domain.state.ScheduleSyncState
import com.example.sportwisdom.features.search.domain.model.TeamDto

data class SearchState(
  val teamsModel: List<TeamDto>,
  val syncState: SearchSyncState
)

sealed class SearchSyncState {
  object Loading : SearchSyncState()
  object Empty : SearchSyncState()
  object Content : SearchSyncState()
  object SideEffect : SearchSyncState()
  data class Message(val msg: String?) : SearchSyncState()
}

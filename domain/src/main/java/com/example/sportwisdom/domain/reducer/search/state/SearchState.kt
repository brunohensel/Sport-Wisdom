package com.example.sportwisdom.domain.reducer.search.state

data class SearchState(
  val teamsModel: List<com.example.sportwisdom.domain.model.TeamDto>,
  val syncState: SearchSyncState
)

sealed class SearchSyncState {
  object Loading : SearchSyncState()
  object Empty : SearchSyncState()
  object Content : SearchSyncState()
  object SideEffect : SearchSyncState()
  data class Message(val msg: String?) : SearchSyncState()
}

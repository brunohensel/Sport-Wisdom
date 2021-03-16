package com.example.sportwisdom.domain.reducer.league.state

data class LeagueState(
  val leagueModel: List<com.example.sportwisdom.domain.model.LeagueDto>,
  val syncState: LeagueSyncState
)

sealed class LeagueSyncState {
  object Loading : LeagueSyncState()
  object Empty : LeagueSyncState()
  object Content : LeagueSyncState()
  data class Message(val msg: String?) : LeagueSyncState()
}
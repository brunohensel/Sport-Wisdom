package com.example.sportwisdom.features.home.league.domain.state

import com.example.sportwisdom.features.home.league.domain.model.LeagueDto

data class LeagueState(
  val leagueModel: List<LeagueDto>,
  val syncState: LeagueSyncState
)

sealed class LeagueSyncState {
  object Loading : LeagueSyncState()
  object Content : LeagueSyncState()
  data class Message(val msg: String?) : LeagueSyncState()
}
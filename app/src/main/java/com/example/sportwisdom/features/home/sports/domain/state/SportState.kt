package com.example.sportwisdom.features.home.sports.domain.state

import com.example.sportwisdom.features.home.sports.domain.model.SportsModel

data class SportState(
  val sportsModel: SportsModel,
  val syncState: SportSyncState
)

sealed class SportSyncState {
  object Loading : SportSyncState()
  object Content : SportSyncState()
  data class Message(val msg: String?) : SportSyncState()
}
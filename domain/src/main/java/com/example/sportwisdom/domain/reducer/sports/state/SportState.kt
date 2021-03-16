package com.example.sportwisdom.domain.reducer.sports.state

import com.example.sportwisdom.domain.model.SportsModel

data class SportState(
  val sportsModel: SportsModel,
  val syncState: SportSyncState
)

sealed class SportSyncState {
  object Loading : SportSyncState()
  object Empty : SportSyncState()
  object Content : SportSyncState()
  data class Message(val msg: String?) : SportSyncState()
}
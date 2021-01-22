package com.example.sportwisdom.features.home.sports.domain.state

import com.example.sportwisdom.features.home.sports.domain.model.SportsModel

data class HomeState(
  val sportsModel: SportsModel,
  val syncState: HomeSyncState
)

sealed class HomeSyncState {
  object Loading : HomeSyncState()
  object Content : HomeSyncState()
  data class Message(val msg: String?) : HomeSyncState()
}
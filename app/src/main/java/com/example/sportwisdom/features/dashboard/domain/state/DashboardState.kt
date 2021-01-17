package com.example.sportwisdom.features.dashboard.domain.state

import com.example.sportwisdom.features.dashboard.domain.model.SportsModel

data class DashboardState(
  val sportsModel: SportsModel,
  val syncState: DashboardSyncState
)

sealed class DashboardSyncState {
  object Loading : DashboardSyncState()
  object Content : DashboardSyncState()
  data class Message(val msg: String?) : DashboardSyncState()
}
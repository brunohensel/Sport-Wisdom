package com.example.sportwisdom.features.dashboard.domain.state

import com.example.sportwisdom.features.dashboard.domain.model.League

data class DashboardState(
  val sportsModel: Map<String, List<League>>,
  val syncState: DashboardSyncState
)

sealed class DashboardSyncState {
  object Loading : DashboardSyncState()
  object Content : DashboardSyncState()
  data class Message(val msg: String?) : DashboardSyncState()
}
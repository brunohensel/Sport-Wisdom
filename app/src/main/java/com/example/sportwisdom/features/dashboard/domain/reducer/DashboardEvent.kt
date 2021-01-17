package com.example.sportwisdom.features.dashboard.domain.reducer

sealed class DashboardEvent {
  object fetchSports : DashboardEvent()
}
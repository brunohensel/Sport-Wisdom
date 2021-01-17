package com.example.sportwisdom.features.dashboard.data

import com.example.sportwisdom.base.BaseAction
import kotlinx.coroutines.flow.Flow

interface DashboardRepository {
  suspend fun fetchAllSports(): Flow<BaseAction>
}
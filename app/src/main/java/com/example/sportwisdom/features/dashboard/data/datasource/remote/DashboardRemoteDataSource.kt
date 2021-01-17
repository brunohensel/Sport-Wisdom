package com.example.sportwisdom.features.dashboard.data.datasource.remote

import com.example.sportwisdom.base.BaseAction
import kotlinx.coroutines.flow.Flow

interface DashboardRemoteDataSource {
  suspend fun fetchAllSports(): Flow<BaseAction>
}
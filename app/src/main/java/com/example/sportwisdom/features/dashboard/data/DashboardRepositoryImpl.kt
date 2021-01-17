package com.example.sportwisdom.features.dashboard.data

import com.example.sportwisdom.base.BaseAction
import com.example.sportwisdom.features.dashboard.data.datasource.remote.DashboardRemoteDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DashboardRepositoryImpl @Inject constructor(private val remoteDataSource: DashboardRemoteDataSource): DashboardRepository {

  override suspend fun fetchAllSports(): Flow<BaseAction> {
   return remoteDataSource.fetchAllSports()
  }
}
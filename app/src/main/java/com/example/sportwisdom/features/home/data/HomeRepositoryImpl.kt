package com.example.sportwisdom.features.home.data

import com.example.sportwisdom.base.BaseAction
import com.example.sportwisdom.features.home.data.datasource.remote.HomeRemoteDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(private val remoteDataSource: HomeRemoteDataSource): HomeRepository {

  override suspend fun fetchAllLeagues(sportType: String): Flow<BaseAction> {
   return remoteDataSource.fetchAllLeagues(sportType)
  }

  override suspend fun fetchAllSports(): Flow<BaseAction> {
    return remoteDataSource.fetchAllSports()
  }

  override suspend fun fetchEvents(leagueId: Int): Flow<BaseAction> {
    return remoteDataSource.fetchEvents(leagueId)
  }
}
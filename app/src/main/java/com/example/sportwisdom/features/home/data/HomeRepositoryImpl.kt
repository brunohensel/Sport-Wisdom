package com.example.sportwisdom.features.home.data

import com.example.sportwisdom.base.BaseAction
import com.example.sportwisdom.features.home.data.datasource.local.HomeLocalDataSource
import com.example.sportwisdom.features.home.data.datasource.remote.HomeRemoteDataSource
import com.example.sportwisdom.features.home.events.domain.model.EventDto
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
  private val remoteDataSource: HomeRemoteDataSource,
  private val localDataSource: HomeLocalDataSource
) : HomeRepository {

  override suspend fun fetchAllLeagues(sportType: String): Flow<BaseAction> {
    return remoteDataSource.fetchAllLeagues(sportType)
  }

  override suspend fun fetchAllSports(): Flow<BaseAction> {
    return remoteDataSource.fetchAllSports()
  }

  override suspend fun fetchEvents(leagueId: Int): Flow<BaseAction> {
    return remoteDataSource.fetchEvents(leagueId)
  }

  override suspend fun insertEvent(eventDto: EventDto): Flow<BaseAction> {
    return localDataSource.insertEvent(eventDto)
  }
}
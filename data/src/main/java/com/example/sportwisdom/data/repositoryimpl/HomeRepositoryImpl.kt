package com.example.sportwisdom.data.repositoryimpl

import com.example.sportwisdom.data.local.datasource.home.HomeLocalDataSource
import com.example.sportwisdom.domain.model.EventDto
import com.example.sportwisdom.domain.repository.HomeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
  private val remoteDataSource: com.example.sportwisdom.data.remote.datasource.home.HomeRemoteDataSource,
  private val localDataSource: HomeLocalDataSource
) : HomeRepository {

  override suspend fun fetchAllLeagues(sportType: String):Flow<com.example.sportwisdom.common.utils.BaseAction<*>> {
    return remoteDataSource.fetchAllLeagues(sportType)
  }

  override suspend fun fetchAllSports(): Flow<com.example.sportwisdom.common.utils.BaseAction<*>> {
    return remoteDataSource.fetchAllSports()
  }

  override suspend fun fetchEvents(leagueId: Int): Flow<com.example.sportwisdom.common.utils.BaseAction<*>> {
    return remoteDataSource.fetchEvents(leagueId)
  }

  override suspend fun insertEvent(eventDto: EventDto): Flow<com.example.sportwisdom.common.utils.BaseAction<*>> {
    return localDataSource.insertEvent(eventDto)
  }
}
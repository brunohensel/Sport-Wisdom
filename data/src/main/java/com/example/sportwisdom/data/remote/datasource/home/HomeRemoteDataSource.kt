package com.example.sportwisdom.data.remote.datasource.home

import com.example.sportwisdom.common.utils.BaseAction
import kotlinx.coroutines.flow.Flow

interface HomeRemoteDataSource {
  suspend fun fetchAllLeagues(sportType: String): Flow<BaseAction<*>>
  suspend fun fetchAllSports(): Flow<BaseAction<*>>
  suspend fun fetchEvents(leagueId: Int): Flow<BaseAction<*>>
}
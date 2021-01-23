package com.example.sportwisdom.features.home.data.datasource.remote

import com.example.sportwisdom.base.BaseAction
import kotlinx.coroutines.flow.Flow

interface HomeRemoteDataSource {
  suspend fun fetchAllLeagues(sportType: String): Flow<BaseAction>
  suspend fun fetchAllSports(): Flow<BaseAction>
  suspend fun fetchEvents(leagueId: Int): Flow<BaseAction>
}
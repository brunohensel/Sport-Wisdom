package com.example.sportwisdom.features.home.data

import com.example.sportwisdom.base.BaseAction
import kotlinx.coroutines.flow.Flow

interface HomeRepository {
  suspend fun fetchAllLeagues(sportType: String): Flow<BaseAction>
  suspend fun fetchAllSports(): Flow<BaseAction>
  suspend fun fetchEvents(leagueId: Int): Flow<BaseAction>
}
package com.example.sportwisdom.features.home.data.datasource.remote

import com.example.sportwisdom.common.utils.BaseAction
import kotlinx.coroutines.flow.Flow

interface HomeRemoteDataSource {
  suspend fun fetchAllLeagues(sportType: String): Flow<com.example.sportwisdom.common.utils.BaseAction<*>>
  suspend fun fetchAllSports(): Flow<com.example.sportwisdom.common.utils.BaseAction<*>>
  suspend fun fetchEvents(leagueId: Int): Flow<com.example.sportwisdom.common.utils.BaseAction<*>>
}
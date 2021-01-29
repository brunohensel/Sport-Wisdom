package com.example.sportwisdom.features.favorite.data.source

import com.example.sportwisdom.base.BaseAction
import kotlinx.coroutines.flow.Flow

interface FavoriteLocalDataSource {
  suspend fun fetchCachedTeams(): Flow<BaseAction<*>>
  suspend fun deleteAllTeams(): Flow<BaseAction<*>>
  suspend fun deleteTeam(teamId: String): Flow<BaseAction<*>>
}
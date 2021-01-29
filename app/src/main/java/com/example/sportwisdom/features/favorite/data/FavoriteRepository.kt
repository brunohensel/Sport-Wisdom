package com.example.sportwisdom.features.favorite.data

import com.example.sportwisdom.base.BaseAction
import kotlinx.coroutines.flow.Flow

interface FavoriteRepository {
  suspend fun fetchCachedTeams(): Flow<BaseAction<*>>
  suspend fun deleteAllTeams(): Flow<BaseAction<*>>
  suspend fun deleteTeam(teamId: String): Flow<BaseAction<*>>
}
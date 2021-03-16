package com.example.sportwisdom.features.favorite.data

import com.example.sportwisdom.common.utils.BaseAction
import kotlinx.coroutines.flow.Flow

interface FavoriteRepository {
  suspend fun fetchCachedTeams(): Flow<com.example.sportwisdom.common.utils.BaseAction<*>>
  suspend fun deleteAllTeams(): Flow<com.example.sportwisdom.common.utils.BaseAction<*>>
  suspend fun deleteTeam(teamId: String): Flow<com.example.sportwisdom.common.utils.BaseAction<*>>
}
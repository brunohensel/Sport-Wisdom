package com.example.sportwisdom.features.favorite.data.source

import com.example.sportwisdom.common.utils.BaseAction
import kotlinx.coroutines.flow.Flow

interface FavoriteLocalDataSource {
  suspend fun fetchCachedTeams(): Flow<com.example.sportwisdom.common.utils.BaseAction<*>>
  suspend fun deleteAllTeams(): Flow<com.example.sportwisdom.common.utils.BaseAction<*>>
  suspend fun deleteTeam(teamId: String): Flow<com.example.sportwisdom.common.utils.BaseAction<*>>
}
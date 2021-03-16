package com.example.sportwisdom.domain.repository

import com.example.sportwisdom.common.utils.BaseAction
import kotlinx.coroutines.flow.Flow

interface FavoriteRepository {
  suspend fun fetchCachedTeams(): Flow<BaseAction<*>>
  suspend fun deleteAllTeams(): Flow<BaseAction<*>>
  suspend fun deleteTeam(teamId: String): Flow<BaseAction<*>>
}
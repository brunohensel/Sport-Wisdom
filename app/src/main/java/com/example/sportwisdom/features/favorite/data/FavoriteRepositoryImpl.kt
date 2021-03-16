package com.example.sportwisdom.features.favorite.data

import com.example.sportwisdom.common.utils.BaseAction
import com.example.sportwisdom.features.favorite.data.source.FavoriteLocalDataSource
import kotlinx.coroutines.flow.Flow

class FavoriteRepositoryImpl(private val localDataSource: FavoriteLocalDataSource) : FavoriteRepository {

  override suspend fun fetchCachedTeams(): Flow<com.example.sportwisdom.common.utils.BaseAction<*>> {
    return localDataSource.fetchCachedTeams()
  }

  override suspend fun deleteAllTeams(): Flow<com.example.sportwisdom.common.utils.BaseAction<*>> {
    return localDataSource.deleteAllTeams()
  }

  override suspend fun deleteTeam(teamId: String): Flow<com.example.sportwisdom.common.utils.BaseAction<*>> {
    return localDataSource.deleteTeam(teamId)
  }
}
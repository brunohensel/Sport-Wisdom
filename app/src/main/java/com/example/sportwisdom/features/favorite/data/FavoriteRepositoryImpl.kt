package com.example.sportwisdom.features.favorite.data

import com.example.sportwisdom.base.BaseAction
import com.example.sportwisdom.features.favorite.data.source.FavoriteLocalDataSource
import kotlinx.coroutines.flow.Flow

class FavoriteRepositoryImpl(private val localDataSource: FavoriteLocalDataSource) : FavoriteRepository {

  override suspend fun fetchCachedTeams(): Flow<BaseAction<*>> {
    return localDataSource.fetchCachedTeams()
  }

  override suspend fun deleteAllTeams(): Flow<BaseAction<*>> {
    return localDataSource.deleteAllTeams()
  }

  override suspend fun deleteTeam(teamId: String): Flow<BaseAction<*>> {
    return localDataSource.deleteTeam(teamId)
  }
}
package com.example.sportwisdom.features.favorite.data.source

import com.example.sportwisdom.base.BaseAction
import com.example.sportwisdom.database.SportWisdomDao
import kotlinx.coroutines.flow.Flow

class FavoriteLocalDataSourceImpl(private val dao: SportWisdomDao) : FavoriteLocalDataSource {

  override suspend fun fetchCachedTeams(): Flow<BaseAction<*>> {
    TODO("Not yet implemented")
  }

  override suspend fun deleteAllTeams(): Flow<BaseAction<*>> {
    TODO("Not yet implemented")
  }

  override suspend fun deleteTeam(teamId: String): Flow<BaseAction<*>> {
    TODO("Not yet implemented")
  }
}
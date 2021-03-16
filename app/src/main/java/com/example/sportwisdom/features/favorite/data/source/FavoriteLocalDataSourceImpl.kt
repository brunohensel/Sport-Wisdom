package com.example.sportwisdom.features.favorite.data.source

import com.example.sportwisdom.common.utils.BaseAction
import com.example.sportwisdom.common.utils.BaseCacheResponseHandler
import com.example.sportwisdom.database.SportWisdomDao
import com.example.sportwisdom.features.search.domain.model.TeamDto
import com.example.sportwisdom.util.safeCacheCall
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FavoriteLocalDataSourceImpl(private val dao: SportWisdomDao) : FavoriteLocalDataSource {

  override suspend fun fetchCachedTeams(): Flow<com.example.sportwisdom.common.utils.BaseAction<*>> = flow {
    val cacheResult = safeCacheCall(IO) { dao.getTeams() }
    val response = object : com.example.sportwisdom.common.utils.BaseCacheResponseHandler<Any, Flow<List<TeamDto>>>(cacheResult) {
      override suspend fun handleSuccess(resultObj: Flow<List<TeamDto>>): com.example.sportwisdom.common.utils.BaseAction<Any> {
        return com.example.sportwisdom.common.utils.BaseAction.CacheSuccess(resultObj)
      }
    }.getResult()
    emit(response)
  }

  override suspend fun deleteAllTeams(): Flow<com.example.sportwisdom.common.utils.BaseAction<*>> = flow {
    val cacheResult = safeCacheCall(IO) { dao.deleteAllTeams() }
    val response = object : com.example.sportwisdom.common.utils.BaseCacheResponseHandler<Any, Unit>(cacheResult) {
      override suspend fun handleSuccess(resultObj: Unit): com.example.sportwisdom.common.utils.BaseAction<Any> {
        return com.example.sportwisdom.common.utils.BaseAction.SideEffect(resultObj)
      }
    }.getResult()
    emit(response)
  }

  override suspend fun deleteTeam(teamId: String): Flow<com.example.sportwisdom.common.utils.BaseAction<*>> = flow {
    val cacheResult = safeCacheCall(IO) { dao.deleteTeamById(teamId) }
    val response = object : com.example.sportwisdom.common.utils.BaseCacheResponseHandler<Any, Int>(cacheResult) {
      override suspend fun handleSuccess(resultObj: Int): com.example.sportwisdom.common.utils.BaseAction<Any> {
        return if (resultObj > 0) com.example.sportwisdom.common.utils.BaseAction.SideEffect(resultObj) else com.example.sportwisdom.common.utils.BaseAction.Failed(reason = "It was not possible delete the team")
      }
    }.getResult()
    emit(response)
  }
}
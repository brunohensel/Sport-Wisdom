package com.example.sportwisdom.features.favorite.data.source

import com.example.sportwisdom.base.BaseAction
import com.example.sportwisdom.base.BaseCacheResponseHandler
import com.example.sportwisdom.database.SportWisdomDao
import com.example.sportwisdom.features.search.domain.model.TeamDto
import com.example.sportwisdom.util.safeCacheCall
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FavoriteLocalDataSourceImpl(private val dao: SportWisdomDao) : FavoriteLocalDataSource {

  override suspend fun fetchCachedTeams(): Flow<BaseAction<*>> = flow {
    val cacheResult = safeCacheCall(IO) { dao.getTeams() }
    val response = object : BaseCacheResponseHandler<Any, Flow<List<TeamDto>>>(cacheResult) {
      override suspend fun handleSuccess(resultObj: Flow<List<TeamDto>>): BaseAction<Any> {
        return BaseAction.CacheSuccess(resultObj)
      }
    }.getResult()
    emit(response)
  }

  override suspend fun deleteAllTeams(): Flow<BaseAction<*>> = flow {
    val cacheResult = safeCacheCall(IO) { dao.deleteAllTeams() }
    val response = object : BaseCacheResponseHandler<Any, Unit>(cacheResult) {
      override suspend fun handleSuccess(resultObj: Unit): BaseAction<Any> {
        return BaseAction.SideEffect(resultObj)
      }
    }.getResult()
    emit(response)
  }

  override suspend fun deleteTeam(teamId: String): Flow<BaseAction<*>> = flow {
    val cacheResult = safeCacheCall(IO) { dao.deleteTeamById(teamId) }
    val response = object : BaseCacheResponseHandler<Any, Int>(cacheResult) {
      override suspend fun handleSuccess(resultObj: Int): BaseAction<Any> {
        return if (resultObj > 0) BaseAction.SideEffect(resultObj) else BaseAction.Failed(reason = "It was not possible delete the team")
      }
    }.getResult()
    emit(response)
  }
}
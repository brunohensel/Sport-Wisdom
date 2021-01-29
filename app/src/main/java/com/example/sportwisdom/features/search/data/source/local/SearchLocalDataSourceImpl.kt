package com.example.sportwisdom.features.search.data.source.local

import com.example.sportwisdom.base.BaseAction
import com.example.sportwisdom.base.BaseCacheResponseHandler
import com.example.sportwisdom.database.SportWisdomDao
import com.example.sportwisdom.features.search.domain.model.TeamDto
import com.example.sportwisdom.util.safeCacheCall
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SearchLocalDataSourceImpl(private val dao: SportWisdomDao) : SearchLocalDataSource {
  override suspend fun insertTeam(team: TeamDto): Flow<BaseAction<*>> = flow {
    val cacheResult = safeCacheCall(IO) { dao.insertTeam(team) }
    val cacheResponse = object : BaseCacheResponseHandler<Any, Long>(cacheResult) {
      override suspend fun handleSuccess(resultObj: Long): BaseAction<Any> {
        return if (resultObj > 0) BaseAction.CacheSuccess(resultObj) else BaseAction.Failed(reason = "Was not possible to insert the team: $team in db")
      }
    }.getResult()
    emit(cacheResponse)
  }
}
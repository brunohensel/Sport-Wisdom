package com.example.sportwisdom.data.local.datasource.search

import com.example.sportwisdom.common.utils.BaseAction
import com.example.sportwisdom.common.utils.BaseCacheResponseHandler
import com.example.sportwisdom.data.local.room.SportWisdomDao
import com.example.sportwisdom.data.utils.safeCacheCall
import com.example.sportwisdom.domain.model.TeamDto
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SearchLocalDataSourceImpl(private val dao: SportWisdomDao) : SearchLocalDataSource {
  override suspend fun insertTeam(team: TeamDto): Flow<BaseAction<*>> = flow {
    val cacheResult = safeCacheCall(IO) { dao.insertTeam(team) }
    val cacheResponse = object : BaseCacheResponseHandler<Any, Long>(cacheResult) {
      override suspend fun handleSuccess(resultObj: Long): BaseAction<Any> {
        return if (resultObj > 0) BaseAction.SideEffect(resultObj) else BaseAction.Failed(reason = "Was not possible to insert the team: $team in db")
      }
    }.getResult()
    emit(cacheResponse)
  }
}
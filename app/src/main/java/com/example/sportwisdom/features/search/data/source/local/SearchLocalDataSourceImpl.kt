package com.example.sportwisdom.features.search.data.source.local

import com.example.sportwisdom.common.utils.BaseAction
import com.example.sportwisdom.common.utils.BaseCacheResponseHandler
import com.example.sportwisdom.database.SportWisdomDao
import com.example.sportwisdom.features.search.domain.model.TeamDto
import com.example.sportwisdom.util.safeCacheCall
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SearchLocalDataSourceImpl(private val dao: SportWisdomDao) : SearchLocalDataSource {
  override suspend fun insertTeam(team: TeamDto): Flow<com.example.sportwisdom.common.utils.BaseAction<*>> = flow {
    val cacheResult = safeCacheCall(IO) { dao.insertTeam(team) }
    val cacheResponse = object : com.example.sportwisdom.common.utils.BaseCacheResponseHandler<Any, Long>(cacheResult) {
      override suspend fun handleSuccess(resultObj: Long): com.example.sportwisdom.common.utils.BaseAction<Any> {
        return if (resultObj > 0) com.example.sportwisdom.common.utils.BaseAction.SideEffect(resultObj) else com.example.sportwisdom.common.utils.BaseAction.Failed(reason = "Was not possible to insert the team: $team in db")
      }
    }.getResult()
    emit(cacheResponse)
  }
}
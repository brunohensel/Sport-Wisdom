package com.example.sportwisdom.features.home.data.datasource.local

import com.example.sportwisdom.base.BaseAction
import com.example.sportwisdom.base.BaseCacheResponseHandler
import com.example.sportwisdom.database.SportWisdomDao
import com.example.sportwisdom.features.home.events.domain.model.EventDto
import com.example.sportwisdom.util.safeCacheCall
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class HomeLocalDataSourceImpl @Inject constructor(private val dao: SportWisdomDao) : HomeLocalDataSource {

  override suspend fun insertEvent(eventDto: EventDto) {
    safeCacheCall(IO) { dao.insertEvent(eventDto) }
  }

  override suspend fun getEvents(): Flow<BaseAction> = flow {
    val cacheResult = safeCacheCall(IO) { dao.getEvents() }
    val cacheResponse = object : BaseCacheResponseHandler<Flow<List<EventDto>>>(cacheResult) {
      override suspend fun handleSuccess(resultObj: Flow<List<EventDto>>): BaseAction.CacheSuccess<Flow<List<EventDto>>> {
        return BaseAction.CacheSuccess(resultObj)
      }
    }.getResult()
    emit(cacheResponse)
  }

  override suspend fun deleteEventById(id: String) {
    safeCacheCall(IO) { dao.deleteEventById(id) }
  }
}
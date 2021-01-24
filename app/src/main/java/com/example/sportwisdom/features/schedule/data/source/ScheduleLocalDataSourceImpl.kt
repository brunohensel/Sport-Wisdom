package com.example.sportwisdom.features.schedule.data.source

import com.example.sportwisdom.base.BaseAction
import com.example.sportwisdom.base.BaseCacheResponseHandler
import com.example.sportwisdom.database.SportWisdomDao
import com.example.sportwisdom.features.home.events.domain.model.EventDto
import com.example.sportwisdom.util.safeCacheCall
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ScheduleLocalDataSourceImpl @Inject constructor(private val dao: SportWisdomDao) : ScheduleLocalDataSource {

  override suspend fun fetchCachedEvents(): Flow<BaseAction> = flow {
    val cacheResult = safeCacheCall(IO) { dao.getEvents() }
    val cacheResponse = object : BaseCacheResponseHandler<Flow<List<EventDto>>>(cacheResult) {
      override suspend fun handleSuccess(resultObj: Flow<List<EventDto>>): BaseAction.CacheSuccess<Flow<List<EventDto>>> {
        return BaseAction.CacheSuccess(resultObj)
      }
    }.getResult()
    emit(cacheResponse)
  }

  override suspend fun deleteAllEvents(): Flow<BaseAction> = flow {
    val cacheResult = safeCacheCall(IO) { dao.deleteAllEvents() }
    val cacheResponse = object : BaseCacheResponseHandler<Unit>(cacheResult) {
      override suspend fun handleSuccess(resultObj: Unit): BaseAction.CacheSuccess<Unit> {
        return BaseAction.CacheSuccess(resultObj)
      }
    }.getResult()
    emit(cacheResponse)
  }

  override suspend fun deleteEvent(eventId: String): Flow<BaseAction> = flow {
    val cacheResult = safeCacheCall(IO) { dao.deleteEventById(eventId) }
    val cacheResponse = object : BaseCacheResponseHandler<Int>(cacheResult) {
      override suspend fun handleSuccess(resultObj: Int): BaseAction.CacheSuccess<Int> {
        return BaseAction.CacheSuccess(resultObj)
      }
    }.getResult()
    emit(cacheResponse)
  }
}
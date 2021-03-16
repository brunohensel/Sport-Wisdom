package com.example.sportwisdom.features.schedule.data.source

import androidx.work.WorkManager
import com.example.sportwisdom.common.utils.BaseAction
import com.example.sportwisdom.common.utils.BaseCacheResponseHandler
import com.example.sportwisdom.common.utils.CacheResult
import com.example.sportwisdom.database.SportWisdomDao
import com.example.sportwisdom.features.home.events.domain.model.EventDto
import com.example.sportwisdom.util.safeCacheCall
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ScheduleLocalDataSourceImpl @Inject constructor(private val dao: SportWisdomDao, private val workManager: WorkManager) : ScheduleLocalDataSource {

  override suspend fun fetchCachedEvents(): Flow<com.example.sportwisdom.common.utils.BaseAction<*>> = flow {
    val cacheResult = safeCacheCall(IO) { dao.getEvents() }
    val cacheResponse = object : com.example.sportwisdom.common.utils.BaseCacheResponseHandler<Any, Flow<List<EventDto>>>(cacheResult) {
      override suspend fun handleSuccess(resultObj: Flow<List<EventDto>>): com.example.sportwisdom.common.utils.BaseAction<Flow<List<EventDto>>> {
        return com.example.sportwisdom.common.utils.BaseAction.CacheSuccess(resultObj)
      }
    }.getResult()
    emit(cacheResponse)
  }

  override suspend fun deleteAllEvents(): Flow<com.example.sportwisdom.common.utils.BaseAction<*>> = flow {
    val cacheResult = safeCacheCall(IO) { dao.deleteAllEvents() }
    val cacheResponse = object : com.example.sportwisdom.common.utils.BaseCacheResponseHandler<Any, Unit>(cacheResult) {
      override suspend fun handleSuccess(resultObj: Unit): com.example.sportwisdom.common.utils.BaseAction<Unit> {
        return com.example.sportwisdom.common.utils.BaseAction.SideEffect(resultObj)
      }
    }.getResult()
    emit(cacheResponse)
  }

  override suspend fun deleteEvent(eventId: String): Flow<com.example.sportwisdom.common.utils.BaseAction<*>> = flow {
    val cacheResult = safeCacheCall(IO) { dao.deleteEventById(eventId) }
    if (cacheResult is com.example.sportwisdom.common.utils.CacheResult.Success) workManager.cancelAllWorkByTag(eventId)
    val cacheResponse = object : com.example.sportwisdom.common.utils.BaseCacheResponseHandler<Any, Int>(cacheResult) {
      override suspend fun handleSuccess(resultObj: Int): com.example.sportwisdom.common.utils.BaseAction<Int> {
        return if (resultObj > 0) com.example.sportwisdom.common.utils.BaseAction.SideEffect(resultObj) else com.example.sportwisdom.common.utils.BaseAction.Failed(reason = "Failed to delete note.")
      }
    }.getResult()
    emit(cacheResponse)
  }
}
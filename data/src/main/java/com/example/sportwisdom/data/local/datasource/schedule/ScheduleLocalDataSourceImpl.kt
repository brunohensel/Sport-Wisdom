package com.example.sportwisdom.data.local.datasource.schedule

import androidx.work.WorkManager
import com.example.sportwisdom.common.utils.BaseAction
import com.example.sportwisdom.common.utils.BaseCacheResponseHandler
import com.example.sportwisdom.data.local.room.SportWisdomDao
import com.example.sportwisdom.data.utils.safeCacheCall
import com.example.sportwisdom.domain.model.EventDto
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ScheduleLocalDataSourceImpl @Inject constructor(
  private val dao: SportWisdomDao,
  private val workManager: WorkManager
) : ScheduleLocalDataSource {

  override suspend fun fetchCachedEvents(): Flow<BaseAction<*>> = flow {
    val cacheResult = safeCacheCall(IO) { dao.getEvents() }
    val cacheResponse = object : BaseCacheResponseHandler<Any, Flow<List<EventDto>>>(cacheResult) {
      override suspend fun handleSuccess(resultObj: Flow<List<EventDto>>): BaseAction<Flow<List<EventDto>>> {
        return BaseAction.CacheSuccess(resultObj)
      }
    }.getResult()
    emit(cacheResponse)
  }

  override suspend fun deleteAllEvents(): Flow<BaseAction<*>> = flow {
    val cacheResult = safeCacheCall(IO) { dao.deleteAllEvents() }
    val cacheResponse = object : BaseCacheResponseHandler<Any, Unit>(cacheResult) {
      override suspend fun handleSuccess(resultObj: Unit): BaseAction<Unit> {
        return BaseAction.SideEffect(resultObj)
      }
    }.getResult()
    emit(cacheResponse)
  }

  override suspend fun deleteEvent(eventId: String): Flow<BaseAction<*>> = flow {
    val cacheResult = safeCacheCall(IO) { dao.deleteEventById(eventId) }
    if (cacheResult is com.example.sportwisdom.common.utils.CacheResult.Success) workManager.cancelAllWorkByTag(eventId)
    val cacheResponse = object : BaseCacheResponseHandler<Any, Int>(cacheResult) {
      override suspend fun handleSuccess(resultObj: Int): BaseAction<Int> {
        return if (resultObj > 0) BaseAction.SideEffect(resultObj) else BaseAction.Failed(reason = "Failed to delete note.")
      }
    }.getResult()
    emit(cacheResponse)
  }
}
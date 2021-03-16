package com.example.sportwisdom.features.schedule.data

import com.example.sportwisdom.common.utils.BaseAction
import com.example.sportwisdom.features.schedule.data.source.ScheduleLocalDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ScheduleRepositoryImpl @Inject constructor(private val localDataSource: ScheduleLocalDataSource) : ScheduleRepository {

  override suspend fun fetchCachedEvents(): Flow<com.example.sportwisdom.common.utils.BaseAction<*>> {
    return localDataSource.fetchCachedEvents()
  }

  override suspend fun deleteAllEvents(): Flow<com.example.sportwisdom.common.utils.BaseAction<*>> {
    return localDataSource.deleteAllEvents()
  }

  override suspend fun deleteEvent(eventId: String): Flow<com.example.sportwisdom.common.utils.BaseAction<*>>  {
    return localDataSource.deleteEvent(eventId)
  }
}
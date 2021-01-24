package com.example.sportwisdom.features.schedule.data

import com.example.sportwisdom.base.BaseAction
import com.example.sportwisdom.features.schedule.data.source.ScheduleLocalDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ScheduleRepositoryImpl @Inject constructor(private val localDataSource: ScheduleLocalDataSource) : ScheduleRepository {

  override suspend fun fetchCachedEvents(): Flow<BaseAction<*>> {
    return localDataSource.fetchCachedEvents()
  }

  override suspend fun deleteAllEvents(): Flow<BaseAction<*>> {
    return localDataSource.deleteAllEvents()
  }

  override suspend fun deleteEvent(eventId: String): Flow<BaseAction<*>>  {
    return localDataSource.deleteEvent(eventId)
  }
}
package com.example.sportwisdom.features.schedule.data

import com.example.sportwisdom.base.BaseAction
import com.example.sportwisdom.features.schedule.data.source.ScheduleLocalDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ScheduleRepositoryImpl @Inject constructor(private val localDataSource: ScheduleLocalDataSource) : ScheduleRepository {

  override suspend fun fetchCachedEvents(): Flow<BaseAction> {
    TODO("Not yet implemented")
  }

  override suspend fun deleteAllEvents(): Flow<BaseAction> {
    TODO("Not yet implemented")
  }

  override suspend fun deleteEvent(eventId: String): Flow<BaseAction> {
    TODO("Not yet implemented")
  }
}
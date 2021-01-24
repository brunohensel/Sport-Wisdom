package com.example.sportwisdom.features.schedule.data.source

import com.example.sportwisdom.base.BaseAction
import com.example.sportwisdom.database.SportWisdomDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ScheduleLocalDataSourceImpl @Inject constructor(private val dao: SportWisdomDao): ScheduleLocalDataSource {

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
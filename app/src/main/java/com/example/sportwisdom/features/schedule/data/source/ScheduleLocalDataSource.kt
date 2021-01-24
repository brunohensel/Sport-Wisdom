package com.example.sportwisdom.features.schedule.data.source

import com.example.sportwisdom.base.BaseAction
import kotlinx.coroutines.flow.Flow

interface ScheduleLocalDataSource {
  suspend fun fetchCachedEvents(): Flow<BaseAction>
  suspend fun deleteAllEvents(): Flow<BaseAction>
  suspend fun deleteEvent(eventId: String): Flow<BaseAction>
}
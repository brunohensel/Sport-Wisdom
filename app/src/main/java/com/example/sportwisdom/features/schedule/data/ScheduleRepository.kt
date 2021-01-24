package com.example.sportwisdom.features.schedule.data

import com.example.sportwisdom.base.BaseAction
import kotlinx.coroutines.flow.Flow

interface ScheduleRepository {
  suspend fun fetchCachedEvents(): Flow<BaseAction>
  suspend fun deleteAllEvents(): Flow<BaseAction>
  suspend fun deleteEvent(eventId: String): Flow<BaseAction>
}
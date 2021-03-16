package com.example.sportwisdom.features.schedule.data

import com.example.sportwisdom.common.utils.BaseAction
import kotlinx.coroutines.flow.Flow

interface ScheduleRepository {
  suspend fun fetchCachedEvents(): Flow<com.example.sportwisdom.common.utils.BaseAction<*>>
  suspend fun deleteAllEvents(): Flow<com.example.sportwisdom.common.utils.BaseAction<*>>
  suspend fun deleteEvent(eventId: String): Flow<com.example.sportwisdom.common.utils.BaseAction<*>>
}
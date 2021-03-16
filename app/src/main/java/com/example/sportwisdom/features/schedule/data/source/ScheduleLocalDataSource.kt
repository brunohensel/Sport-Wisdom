package com.example.sportwisdom.features.schedule.data.source

import com.example.sportwisdom.common.utils.BaseAction
import kotlinx.coroutines.flow.Flow

interface ScheduleLocalDataSource {
  suspend fun fetchCachedEvents(): Flow<com.example.sportwisdom.common.utils.BaseAction<*>>
  suspend fun deleteAllEvents(): Flow<com.example.sportwisdom.common.utils.BaseAction<*>>
  suspend fun deleteEvent(eventId: String): Flow<com.example.sportwisdom.common.utils.BaseAction<*>>
}
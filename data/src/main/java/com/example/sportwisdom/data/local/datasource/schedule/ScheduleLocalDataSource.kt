package com.example.sportwisdom.data.local.datasource.schedule

import kotlinx.coroutines.flow.Flow

interface ScheduleLocalDataSource {
  suspend fun fetchCachedEvents(): Flow<com.example.sportwisdom.common.utils.BaseAction<*>>
  suspend fun deleteAllEvents(): Flow<com.example.sportwisdom.common.utils.BaseAction<*>>
  suspend fun deleteEvent(eventId: String): Flow<com.example.sportwisdom.common.utils.BaseAction<*>>
}
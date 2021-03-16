package com.example.sportwisdom.domain.repository

import com.example.sportwisdom.common.utils.BaseAction
import kotlinx.coroutines.flow.Flow

interface ScheduleRepository {
  suspend fun fetchCachedEvents(): Flow<BaseAction<*>>
  suspend fun deleteAllEvents(): Flow<BaseAction<*>>
  suspend fun deleteEvent(eventId: String): Flow<BaseAction<*>>
}
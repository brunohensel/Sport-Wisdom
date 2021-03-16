package com.example.sportwisdom.domain.repository

import com.example.sportwisdom.common.utils.BaseAction
import com.example.sportwisdom.domain.model.EventDto
import kotlinx.coroutines.flow.Flow

interface HomeRepository {
  suspend fun fetchAllLeagues(sportType: String): Flow<BaseAction<*>>
  suspend fun fetchAllSports(): Flow<BaseAction<*>>
  suspend fun fetchEvents(leagueId: Int): Flow<BaseAction<*>>
  suspend fun insertEvent(eventDto: EventDto): Flow<BaseAction<*>>
}
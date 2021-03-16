package com.example.sportwisdom.features.home.data

import com.example.sportwisdom.common.utils.BaseAction
import com.example.sportwisdom.features.home.events.domain.model.EventDto
import kotlinx.coroutines.flow.Flow

interface HomeRepository {
  suspend fun fetchAllLeagues(sportType: String): Flow<com.example.sportwisdom.common.utils.BaseAction<*>>
  suspend fun fetchAllSports(): Flow<com.example.sportwisdom.common.utils.BaseAction<*>>
  suspend fun fetchEvents(leagueId: Int): Flow<com.example.sportwisdom.common.utils.BaseAction<*>>
  suspend fun insertEvent(eventDto: EventDto): Flow<com.example.sportwisdom.common.utils.BaseAction<*>>
}
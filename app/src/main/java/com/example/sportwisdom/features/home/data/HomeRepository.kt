package com.example.sportwisdom.features.home.data

import com.example.sportwisdom.base.BaseAction
import com.example.sportwisdom.features.home.events.domain.model.EventDto
import kotlinx.coroutines.flow.Flow

interface HomeRepository {
  suspend fun fetchAllLeagues(sportType: String): Flow<BaseAction>
  suspend fun fetchAllSports(): Flow<BaseAction>
  suspend fun fetchEvents(leagueId: Int): Flow<BaseAction>
  suspend fun insertEvent(eventDto: EventDto): Flow<BaseAction>
}
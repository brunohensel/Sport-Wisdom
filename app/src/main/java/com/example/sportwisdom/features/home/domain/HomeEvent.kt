package com.example.sportwisdom.features.home.domain

import com.example.sportwisdom.features.home.events.domain.model.EventDto

sealed class HomeEvent {
  data class FetchLeagues(val sportType: String) : HomeEvent()
  data class FetchEvents(val leagueId: Int) : HomeEvent()
  data class InsertEvent(val eventDto: EventDto): HomeEvent()
  object FetchSports : HomeEvent()
}
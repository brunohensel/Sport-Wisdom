package com.example.sportwisdom.features.home.domain

import com.example.sportwisdom.features.home.events.domain.model.EventDto

sealed class HomeIntents {
  data class FetchLeagues(val sportType: String) : HomeIntents()
  data class FetchEvents(val leagueId: Int) : HomeIntents()
  data class InsertEvent(val eventDto: EventDto): HomeIntents()
  object FetchSports : HomeIntents()
}
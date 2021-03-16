package com.example.sportwisdom.domain.reducer.home

sealed class HomeIntents {
  data class FetchLeagues(val sportType: String) : HomeIntents()
  data class FetchEvents(val leagueId: Int) : HomeIntents()
  data class InsertEvent(val eventDto: com.example.sportwisdom.domain.model.EventDto): HomeIntents()
  object FetchSports : HomeIntents()
}
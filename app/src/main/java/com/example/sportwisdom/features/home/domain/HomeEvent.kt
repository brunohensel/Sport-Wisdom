package com.example.sportwisdom.features.home.domain

sealed class HomeEvent {
  data class FetchLeagues(val sportType: String) : HomeEvent()
  data class FetchEvents(val leagueId: Int) : HomeEvent()
  object FetchSports : HomeEvent()
}
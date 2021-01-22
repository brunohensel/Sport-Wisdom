package com.example.sportwisdom.features.home.sports.domain.reducer

sealed class HomeEvent {
  object FetchLeagues : HomeEvent()
  object FetchSports : HomeEvent()
}
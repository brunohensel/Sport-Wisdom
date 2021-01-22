package com.example.sportwisdom.features.home.domain

sealed class HomeEvent {
  data class FetchLeagues(val sportType: String) : HomeEvent()
  object FetchSports : HomeEvent()
}
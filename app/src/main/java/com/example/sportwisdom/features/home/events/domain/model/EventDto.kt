package com.example.sportwisdom.features.home.events.domain.model

data class EventDto(
  val dateEvent: String,
  val dateEventLocal: String,
  val idAPIfootball: String,
  val idAwayTeam: String,
  val idEvent: String,
  val idHomeTeam: String,
  val idLeague: String,
  val intRound: String,
  val strAwayGoalDetails: String,
  val strAwayTeam: String,
  val strCity: String,
  val strCountry: String,
  val strEvent: String,
  val strHomeTeam: String,
  val strLeague: String,
  val strSeason: String,
  val strSport: String,
  val strStatus: String,
  val strThumb: String?,
  val strTime: String,
  val strTimeLocal: String,
  val strTimestamp: String,
  val strVenue: String
)
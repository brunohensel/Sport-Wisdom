package com.example.sportwisdom.features.home.events.domain.model

data class EventDto(
  val idEvent: String,
  val idLeague: String,
  val strEvent: String,
  val strLeague: String,
  val strThumb: String?,
  val strTimestamp: String?,
  val strVenue: String?,
  val dateEvent: String,
  val strTime: String
)
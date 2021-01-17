package com.example.sportwisdom.features.dashboard.domain.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SportsModel(
  val leagues: List<League>
)

data class League(
  @Json(name = "idLeague") val id: String,
  @Json(name = "strLeague") val league: String,
  @Json(name = "strSport") val sportType: String
)
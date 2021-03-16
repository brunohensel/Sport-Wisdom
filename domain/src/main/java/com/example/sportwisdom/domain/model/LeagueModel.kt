package com.example.sportwisdom.domain.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LeagueModel(val leagues: List<LeagueDto>)

data class LeagueDto(
  @Json(name = "idLeague") val id: String,
  @Json(name = "strLeague") val league: String,
  @Json(name = "strSport") val sportType: String
)
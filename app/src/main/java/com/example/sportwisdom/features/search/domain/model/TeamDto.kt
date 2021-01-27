package com.example.sportwisdom.features.search.domain.model

import java.io.Serializable

data class TeamDto(
  val idTeam: String,
  val strTeam: String,
  val strAlternate: String?,
  val intFormedYear: String,
  val strLeague: String,
  val strStadium: String?,
  val strStadiumThumb: String?,
  val strStadiumDescription: String?,
  val strStadiumLocation: String?,
  val strDescriptionEN: String,
  val strTeamBadge: String?,
  val strTeamJersey: String?,
  val strTeamLogo: String?,
): Serializable
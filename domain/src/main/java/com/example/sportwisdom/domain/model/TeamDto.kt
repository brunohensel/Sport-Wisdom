package com.example.sportwisdom.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "teams_table")
data class TeamDto(
  @PrimaryKey(autoGenerate = false)
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
  val strTeamBanner: String?,
): Serializable
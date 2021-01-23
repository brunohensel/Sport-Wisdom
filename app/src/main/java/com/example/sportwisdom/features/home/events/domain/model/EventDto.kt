package com.example.sportwisdom.features.home.events.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "events_table")
data class EventDto(
  @PrimaryKey(autoGenerate = false)
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
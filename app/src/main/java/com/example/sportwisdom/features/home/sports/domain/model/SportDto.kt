package com.example.sportwisdom.features.home.sports.domain.model

import java.io.Serializable

data class SportDto(
  val idSport: String,
  val strSport: String,
  val strSportThumb: String
): Serializable
package com.example.sportwisdom.features.home.sports.domain.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SportsModel(val sports: List<SportDto>)

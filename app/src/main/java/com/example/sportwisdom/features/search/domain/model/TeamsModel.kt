package com.example.sportwisdom.features.search.domain.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TeamsModel(val teams: List<TeamDto>?)


package com.example.sportwisdom.domain.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TeamsModel(val teams: List<com.example.sportwisdom.domain.model.TeamDto>?)


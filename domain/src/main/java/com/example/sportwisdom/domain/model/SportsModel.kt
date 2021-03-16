package com.example.sportwisdom.domain.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SportsModel(val sports: List<SportDto>)

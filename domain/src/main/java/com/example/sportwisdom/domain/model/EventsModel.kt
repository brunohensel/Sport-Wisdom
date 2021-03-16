package com.example.sportwisdom.domain.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class EventsModel(val events: List<com.example.sportwisdom.domain.model.EventDto>?)


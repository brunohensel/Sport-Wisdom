package com.example.sportwisdom.features.home.events.domain.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class EventsModel(val events: List<EventDto>?)


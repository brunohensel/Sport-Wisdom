package com.example.sportwisdom.features.home.data.datasource.local

import com.example.sportwisdom.common.utils.BaseAction
import com.example.sportwisdom.features.home.events.domain.model.EventDto
import kotlinx.coroutines.flow.Flow

interface HomeLocalDataSource {
  suspend fun insertEvent(eventDto: EventDto): Flow<com.example.sportwisdom.common.utils.BaseAction<*>>
}
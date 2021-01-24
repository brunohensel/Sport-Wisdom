package com.example.sportwisdom.features.home.data.datasource.local

import com.example.sportwisdom.base.BaseAction
import com.example.sportwisdom.features.home.events.domain.model.EventDto
import kotlinx.coroutines.flow.Flow

interface HomeLocalDataSource {
  suspend fun insertEvent(eventDto: EventDto): Flow<BaseAction>
}
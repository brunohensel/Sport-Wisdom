package com.example.sportwisdom.data.local.datasource.home

import com.example.sportwisdom.common.utils.BaseAction
import com.example.sportwisdom.domain.model.EventDto
import kotlinx.coroutines.flow.Flow

interface HomeLocalDataSource {
  suspend fun insertEvent(eventDto: EventDto): Flow<BaseAction<*>>
}
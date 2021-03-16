package com.example.sportwisdom.data.local.datasource.search

import com.example.sportwisdom.common.utils.BaseAction
import com.example.sportwisdom.domain.model.TeamDto
import kotlinx.coroutines.flow.Flow

interface SearchLocalDataSource {
  suspend fun insertTeam(team: TeamDto): Flow<BaseAction<*>>
}
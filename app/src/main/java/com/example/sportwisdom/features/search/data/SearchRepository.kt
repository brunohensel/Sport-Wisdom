package com.example.sportwisdom.features.search.data

import com.example.sportwisdom.common.utils.BaseAction
import com.example.sportwisdom.features.search.domain.model.TeamDto
import kotlinx.coroutines.flow.Flow

interface SearchRepository {
  suspend fun searchTeams(teamName: String): Flow<com.example.sportwisdom.common.utils.BaseAction<*>>
  suspend fun insertTeam(team: TeamDto): Flow<com.example.sportwisdom.common.utils.BaseAction<*>>
}
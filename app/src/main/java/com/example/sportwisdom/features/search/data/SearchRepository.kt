package com.example.sportwisdom.features.search.data

import com.example.sportwisdom.base.BaseAction
import com.example.sportwisdom.features.search.domain.model.TeamDto
import kotlinx.coroutines.flow.Flow

interface SearchRepository {
  suspend fun searchTeams(teamName: String): Flow<BaseAction<*>>
  suspend fun insertTeam(team: TeamDto): Flow<BaseAction<*>>
}
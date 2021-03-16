package com.example.sportwisdom.domain.repository

import com.example.sportwisdom.common.utils.BaseAction
import com.example.sportwisdom.domain.model.TeamDto
import kotlinx.coroutines.flow.Flow

interface SearchRepository {
  suspend fun searchTeams(teamName: String): Flow<BaseAction<*>>
  suspend fun insertTeam(team: TeamDto): Flow<BaseAction<*>>
}
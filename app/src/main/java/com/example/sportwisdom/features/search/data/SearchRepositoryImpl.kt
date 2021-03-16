package com.example.sportwisdom.features.search.data

import com.example.sportwisdom.common.utils.BaseAction
import com.example.sportwisdom.features.search.data.source.local.SearchLocalDataSource
import com.example.sportwisdom.features.search.data.source.remote.SearchRemoteDataSource
import com.example.sportwisdom.features.search.domain.model.TeamDto
import kotlinx.coroutines.flow.Flow

class SearchRepositoryImpl(private val remoteDataSource: SearchRemoteDataSource, private val localDataSource: SearchLocalDataSource) : SearchRepository {

  override suspend fun searchTeams(teamName: String): Flow<com.example.sportwisdom.common.utils.BaseAction<*>> {
    return remoteDataSource.searchTeams(teamName)
  }

  override suspend fun insertTeam(team: TeamDto): Flow<com.example.sportwisdom.common.utils.BaseAction<*>> {
    return localDataSource.insertTeam(team)
  }
}
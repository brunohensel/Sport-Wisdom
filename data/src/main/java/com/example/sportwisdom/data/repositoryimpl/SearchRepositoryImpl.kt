package com.example.sportwisdom.data.repositoryimpl

import com.example.sportwisdom.data.local.datasource.search.SearchLocalDataSource
import com.example.sportwisdom.data.remote.datasource.search.SearchRemoteDataSource
import com.example.sportwisdom.domain.model.TeamDto
import com.example.sportwisdom.domain.repository.SearchRepository
import kotlinx.coroutines.flow.Flow

class SearchRepositoryImpl(
  private val remoteDataSource: SearchRemoteDataSource,
  private val localDataSource: SearchLocalDataSource) : SearchRepository {

  override suspend fun searchTeams(teamName: String): Flow<com.example.sportwisdom.common.utils.BaseAction<*>> {
    return remoteDataSource.searchTeams(teamName)
  }

  override suspend fun insertTeam(team: TeamDto): Flow<com.example.sportwisdom.common.utils.BaseAction<*>> {
    return localDataSource.insertTeam(team)
  }
}
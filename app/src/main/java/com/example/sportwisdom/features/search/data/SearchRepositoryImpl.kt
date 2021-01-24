package com.example.sportwisdom.features.search.data

import com.example.sportwisdom.base.BaseAction
import com.example.sportwisdom.features.search.data.source.remote.SearchRemoteDataSource
import kotlinx.coroutines.flow.Flow

class SearchRepositoryImpl(private val remoteDataSource: SearchRemoteDataSource) : SearchRepository {

  override suspend fun searchTeams(teamName: String): Flow<BaseAction<*>> {
    return remoteDataSource.searchTeams(teamName)
  }
}
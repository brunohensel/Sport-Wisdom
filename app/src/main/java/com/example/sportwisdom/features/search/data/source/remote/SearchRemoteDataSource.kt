package com.example.sportwisdom.features.search.data.source.remote

import com.example.sportwisdom.base.BaseAction
import kotlinx.coroutines.flow.Flow

interface SearchRemoteDataSource {
  suspend fun searchTeams(teamName: String): Flow<BaseAction<*>>
}
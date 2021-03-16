package com.example.sportwisdom.data.remote.datasource.search

import com.example.sportwisdom.common.utils.BaseAction
import kotlinx.coroutines.flow.Flow

interface SearchRemoteDataSource {
  suspend fun searchTeams(teamName: String): Flow<com.example.sportwisdom.common.utils.BaseAction<*>>
}
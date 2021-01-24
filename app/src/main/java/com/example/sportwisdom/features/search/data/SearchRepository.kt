package com.example.sportwisdom.features.search.data

import com.example.sportwisdom.base.BaseAction
import kotlinx.coroutines.flow.Flow

interface SearchRepository {
  suspend fun searchTeams(teamName: String): Flow<BaseAction<*>>
}
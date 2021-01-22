package com.example.sportwisdom.features.home.sports.data

import com.example.sportwisdom.base.BaseAction
import kotlinx.coroutines.flow.Flow

interface HomeRepository {
  suspend fun fetchAllLeagues(): Flow<BaseAction>
  suspend fun fetchAllSports(): Flow<BaseAction>
}
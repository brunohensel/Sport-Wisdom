package com.example.sportwisdom.features.home.sports.data.datasource.remote

import com.example.sportwisdom.base.BaseAction
import kotlinx.coroutines.flow.Flow

interface HomeRemoteDataSource {
  suspend fun fetchAllLeagues(): Flow<BaseAction>
  suspend fun fetchAllSports(): Flow<BaseAction>
}
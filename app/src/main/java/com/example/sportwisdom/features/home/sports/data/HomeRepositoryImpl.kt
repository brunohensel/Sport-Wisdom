package com.example.sportwisdom.features.home.sports.data

import com.example.sportwisdom.base.BaseAction
import com.example.sportwisdom.features.home.sports.data.datasource.remote.HomeRemoteDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(private val remoteDataSource: HomeRemoteDataSource): HomeRepository {

  override suspend fun fetchAllLeagues(): Flow<BaseAction> {
   return remoteDataSource.fetchAllLeagues()
  }

  override suspend fun fetchAllSports(): Flow<BaseAction> {
    return remoteDataSource.fetchAllSports()
  }
}
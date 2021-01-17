package com.example.sportwisdom.features.dashboard.data.datasource.remote

import com.example.sportwisdom.base.BaseAction
import com.example.sportwisdom.base.BaseApiResponseHandler
import com.example.sportwisdom.features.apiservice.SportApiService
import com.example.sportwisdom.features.dashboard.domain.model.League
import com.example.sportwisdom.util.safeApiCall
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DashboardRemoteDataSourceImpl @Inject constructor(private val sportApiService: SportApiService) : DashboardRemoteDataSource {

  override suspend fun fetchAllSports(): Flow<BaseAction> = flow {
    val networkResult = safeApiCall(IO) { sportApiService.fetchAllLeagues().leagues.groupBy { it.sportType } }
    val response = object : BaseApiResponseHandler<Map<String, List<League>>>(apiResult = networkResult) {
      override suspend fun handleSuccess(resultObj: Map<String, List<League>>): BaseAction.Success<Map<String, List<League>>> {
        return BaseAction.Success(resultObj)
      }
    }.getResult()
    emit(response)
  }
}
package com.example.sportwisdom.features.home.sports.data.datasource.remote

import com.example.sportwisdom.base.BaseAction
import com.example.sportwisdom.base.BaseApiResponseHandler
import com.example.sportwisdom.features.apiservice.SportApiService
import com.example.sportwisdom.features.home.sports.domain.model.LeagueDto
import com.example.sportwisdom.features.home.sports.domain.model.SportsModel
import com.example.sportwisdom.util.safeApiCall
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class HomeRemoteDataSourceImpl @Inject constructor(private val sportApiService: SportApiService) : HomeRemoteDataSource {

  override suspend fun fetchAllLeagues(): Flow<BaseAction> = flow {
    val networkResult = safeApiCall(IO) { sportApiService.fetchAllLeagues().leagues.groupBy { it.sportType } }
    val response = object : BaseApiResponseHandler<Map<String, List<LeagueDto>>>(apiResult = networkResult) {
      override suspend fun handleSuccess(resultObj: Map<String, List<LeagueDto>>): BaseAction.Success<Map<String, List<LeagueDto>>> {
        return BaseAction.Success(resultObj)
      }
    }.getResult()
    emit(response)
  }

  override suspend fun fetchAllSports(): Flow<BaseAction> = flow {
    val networkResult = safeApiCall(IO){sportApiService.fetchAllSports()}
    val response = object : BaseApiResponseHandler<SportsModel>(networkResult){
      override suspend fun handleSuccess(resultObj: SportsModel): BaseAction.Success<SportsModel> {
        return BaseAction.Success(resultObj)
      }
    }.getResult()
    emit(response)
  }
}
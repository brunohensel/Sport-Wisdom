package com.example.sportwisdom.features.home.data.datasource.remote

import com.example.sportwisdom.base.BaseAction
import com.example.sportwisdom.base.BaseApiResponseHandler
import com.example.sportwisdom.features.apiservice.SportApiService
import com.example.sportwisdom.features.home.events.domain.model.EventDto
import com.example.sportwisdom.features.home.league.domain.model.LeagueDto
import com.example.sportwisdom.features.home.sports.domain.model.SportsModel
import com.example.sportwisdom.util.safeApiCall
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class HomeRemoteDataSourceImpl @Inject constructor(private val sportApiService: SportApiService) : HomeRemoteDataSource {

  override suspend fun fetchAllLeagues(sportType: String): Flow<BaseAction> = flow {
    val networkResult = safeApiCall(IO) { sportApiService.fetchAllLeagues().leagues }
    val response = object : BaseApiResponseHandler<List<LeagueDto>>(apiResult = networkResult) {
      override suspend fun handleSuccess(resultObj: List<LeagueDto>): BaseAction.Success<List<LeagueDto>> {
        return BaseAction.Success(resultObj.filter { it.sportType == sportType })
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

  override suspend fun fetchEvents(leagueId: Int): Flow<BaseAction> = flow{
    val networkResult = safeApiCall(IO){sportApiService.fetchEvents(leagueId).events}
    val response = object : BaseApiResponseHandler<List<EventDto>?>(apiResult = networkResult){
      override suspend fun handleSuccess(resultObj: List<EventDto>?): BaseAction.Success<List<EventDto>?> {
        return BaseAction.Success(resultObj)
      }
    }.getResult()
    emit(response)
  }
}
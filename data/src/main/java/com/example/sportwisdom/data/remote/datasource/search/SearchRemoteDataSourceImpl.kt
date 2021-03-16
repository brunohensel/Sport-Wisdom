package com.example.sportwisdom.data.remote.datasource.search

import com.example.sportwisdom.common.utils.BaseAction
import com.example.sportwisdom.common.utils.BaseApiResponseHandler
import com.example.sportwisdom.data.remote.apiservice.SportApiService
import com.example.sportwisdom.data.utils.safeApiCall
import com.example.sportwisdom.domain.model.TeamDto
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SearchRemoteDataSourceImpl(private val sportApiService: SportApiService) : SearchRemoteDataSource {

  override suspend fun searchTeams(teamName: String): Flow<BaseAction<*>> = flow {
    val networkResult = safeApiCall(IO){sportApiService.fetchTeams(teamName).teams}
    val networkResponse = object : BaseApiResponseHandler<Any, List<TeamDto>?>(networkResult){
      override suspend fun handleSuccess(resultObj: List<TeamDto>?): BaseAction<Any> {
        return if (resultObj.isNullOrEmpty()) BaseAction.EmptyResult else BaseAction.RemoteSuccess(resultObj.withImage())
      }
    }.getResult()
    emit(networkResponse)
  }

  private fun List<TeamDto>.withImage()= filter{
    !it.strStadiumThumb.isNullOrEmpty() && !it.strTeamLogo.isNullOrEmpty() && !it.strTeamBadge.isNullOrEmpty() && !it.strTeamBanner.isNullOrEmpty()}
}
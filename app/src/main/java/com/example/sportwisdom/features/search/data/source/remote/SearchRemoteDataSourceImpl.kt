package com.example.sportwisdom.features.search.data.source.remote

import com.example.sportwisdom.common.utils.BaseAction
import com.example.sportwisdom.common.utils.BaseApiResponseHandler
import com.example.sportwisdom.features.apiservice.SportApiService
import com.example.sportwisdom.features.search.domain.model.TeamDto
import com.example.sportwisdom.util.safeApiCall
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SearchRemoteDataSourceImpl(private val sportApiService: SportApiService) : SearchRemoteDataSource {

  override suspend fun searchTeams(teamName: String): Flow<com.example.sportwisdom.common.utils.BaseAction<*>> = flow {
    val networkResult = safeApiCall(IO){sportApiService.fetchTeams(teamName).teams}
    val networkResponse = object : com.example.sportwisdom.common.utils.BaseApiResponseHandler<Any, List<TeamDto>?>(networkResult){
      override suspend fun handleSuccess(resultObj: List<TeamDto>?): com.example.sportwisdom.common.utils.BaseAction<Any> {
        return if (resultObj.isNullOrEmpty()) com.example.sportwisdom.common.utils.BaseAction.EmptyResult else com.example.sportwisdom.common.utils.BaseAction.RemoteSuccess(resultObj.withImage())
      }
    }.getResult()
    emit(networkResponse)
  }

  private fun List<TeamDto>.withImage()= filter{
    !it.strStadiumThumb.isNullOrEmpty() && !it.strTeamLogo.isNullOrEmpty() && !it.strTeamBadge.isNullOrEmpty() && !it.strTeamBanner.isNullOrEmpty()}
}
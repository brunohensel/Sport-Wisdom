package com.example.sportwisdom.features.apiservice

import com.example.sportwisdom.features.dashboard.domain.model.SportsModel
import retrofit2.http.GET

interface SportApiService {

  @GET("all_leagues.php")
  suspend fun fetchAllLeagues(): SportsModel
}
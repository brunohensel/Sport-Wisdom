package com.example.sportwisdom.features.apiservice

import retrofit2.http.GET

interface SportApiService {

  @GET("all_leagues.php")
  suspend fun fetchAllLeagues()
}
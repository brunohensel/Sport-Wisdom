package com.example.sportwisdom.features.apiservice

import com.example.sportwisdom.features.home.league.domain.model.LeagueModel
import com.example.sportwisdom.features.home.sports.domain.model.SportsModel
import retrofit2.http.GET

interface SportApiService {

  @GET("all_leagues.php")
  suspend fun fetchAllLeagues(): LeagueModel

  @GET("all_sports.php")
  suspend fun fetchAllSports(): SportsModel
}
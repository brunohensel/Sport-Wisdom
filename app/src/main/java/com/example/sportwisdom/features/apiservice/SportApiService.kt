package com.example.sportwisdom.features.apiservice

import com.example.sportwisdom.features.home.events.domain.model.EventsModel
import com.example.sportwisdom.features.home.league.domain.model.LeagueModel
import com.example.sportwisdom.features.home.sports.domain.model.SportsModel
import com.example.sportwisdom.features.search.domain.model.TeamsModel
import retrofit2.http.GET
import retrofit2.http.Query

interface SportApiService {

  @GET("all_leagues.php")
  suspend fun fetchAllLeagues(): LeagueModel

  @GET("all_sports.php")
  suspend fun fetchAllSports(): SportsModel

  @GET("eventsnextleague.php?")
  suspend fun fetchEvents(@Query("id") id: Int): EventsModel

  @GET("searchteams.php?")
  suspend fun fetchTeams(@Query("t") teamName: String): TeamsModel
}
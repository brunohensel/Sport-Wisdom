package com.example.sportwisdom.domain.reducer.favorite

sealed class FavoriteIntents {
  object FetchCachedTeams : FavoriteIntents()
  object DeleteAllTeams : FavoriteIntents()
  data class DeleteTeam(val teamId: String) : FavoriteIntents()
}
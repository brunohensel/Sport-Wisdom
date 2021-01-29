package com.example.sportwisdom.features.favorite.domain.reducer

sealed class FavoriteIntents {
  object FetchCachedTeams : FavoriteIntents()
  object DeleteAllTeams : FavoriteIntents()
  data class DeleteTeam(val teamId: String) : FavoriteIntents()
}
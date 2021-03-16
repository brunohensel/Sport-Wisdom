package com.example.sportwisdom.domain.reducer.search

sealed class SearchIntents {
  data class SearchForTeamsByName(val teamName: String) : SearchIntents()
  data class AddToFavorites(val team: com.example.sportwisdom.domain.model.TeamDto) : SearchIntents()
}

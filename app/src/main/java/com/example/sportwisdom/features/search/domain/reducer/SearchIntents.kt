package com.example.sportwisdom.features.search.domain.reducer

import com.example.sportwisdom.features.search.domain.model.TeamDto

sealed class SearchIntents {
  data class SearchForTeamsByName(val teamName: String) : SearchIntents()
  data class AddToFavorites(val team: TeamDto) : SearchIntents()
}

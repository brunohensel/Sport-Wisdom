package com.example.sportwisdom.features.search.domain.reducer

sealed class SearchIntents {
  data class SearchForTeamsByName(val teamName: String) : SearchIntents()
}

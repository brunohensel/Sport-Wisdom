package com.example.sportwisdom.features.favorite.domain.state

import com.example.sportwisdom.features.search.domain.model.TeamDto
import kotlinx.coroutines.flow.Flow

data class FavoriteState(
  val favoriteModel: Flow<List<TeamDto>>,
  val syncState: FavoriteSyncState
)

sealed class FavoriteSyncState {
  object Loading : FavoriteSyncState()
  object Content : FavoriteSyncState()
  object SideEffect : FavoriteSyncState()
  object Empty : FavoriteSyncState()
  data class Message(val msg: String?) : FavoriteSyncState()
}
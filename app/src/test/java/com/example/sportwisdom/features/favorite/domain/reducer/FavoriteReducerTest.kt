package com.example.sportwisdom.features.favorite.domain.reducer

import com.example.sportwisdom.domain.reducer.favorite.state.FavoriteState
import com.example.sportwisdom.domain.reducer.favorite.state.FavoriteSyncState
import com.example.sportwisdom.domain.model.TeamDto
import com.google.common.truth.Truth
import kotlinx.coroutines.flow.flowOf
import org.junit.Test

class FavoriteReducerTest {

  @Test
  fun loading_test() {
    //Given
    val action = com.example.sportwisdom.common.utils.BaseAction.Executing
    val teams = flowOf(emptyList<com.example.sportwisdom.domain.model.TeamDto>())
    val currentState = com.example.sportwisdom.domain.reducer.favorite.state.FavoriteState(
      teams,
      syncState = com.example.sportwisdom.domain.reducer.favorite.state.FavoriteSyncState.Content
    )

    //When
    val newState = com.example.sportwisdom.domain.reducer.favorite.FavoriteReducer().invoke(currentState, action)

    //Then
    Truth.assertThat(newState.syncState).isEqualTo(com.example.sportwisdom.domain.reducer.favorite.state.FavoriteSyncState.Loading)
  }

  @Test
  fun content_test() {
    //Given
    val teams = flowOf(emptyList<com.example.sportwisdom.domain.model.TeamDto>())
    val action = com.example.sportwisdom.common.utils.BaseAction.CacheSuccess(teams)
    val currentState = com.example.sportwisdom.domain.reducer.favorite.state.FavoriteState(
      teams,
      com.example.sportwisdom.domain.reducer.favorite.state.FavoriteSyncState.Loading
    )

    //When
    val newState = com.example.sportwisdom.domain.reducer.favorite.FavoriteReducer().invoke(currentState, action)

    //Then
    Truth.assertThat(newState.syncState).isEqualTo(com.example.sportwisdom.domain.reducer.favorite.state.FavoriteSyncState.Content)
    Truth.assertThat(newState.favoriteModel).isEqualTo(teams)
  }

  @Test
  fun message_test() {
    //Given
    val message = "Error Test"
    val action = com.example.sportwisdom.common.utils.BaseAction.Failed(reason = message)
    val teams = flowOf(emptyList<com.example.sportwisdom.domain.model.TeamDto>())
    val currentState = com.example.sportwisdom.domain.reducer.favorite.state.FavoriteState(
      teams,
      com.example.sportwisdom.domain.reducer.favorite.state.FavoriteSyncState.Loading
    )

    //When
    val newState = com.example.sportwisdom.domain.reducer.favorite.FavoriteReducer().invoke(currentState, action)

    //Then
    Truth.assertThat(newState.syncState).isEqualTo(com.example.sportwisdom.domain.reducer.favorite.state.FavoriteSyncState.Message(message))
    Truth.assertThat(newState.favoriteModel).isEqualTo(teams)
  }

  @Test
  fun empty_test() {
    //Given
    val action = com.example.sportwisdom.common.utils.BaseAction.EmptyResult
    val teams = flowOf(emptyList<com.example.sportwisdom.domain.model.TeamDto>())
    val currentState = com.example.sportwisdom.domain.reducer.favorite.state.FavoriteState(
      teams,
      com.example.sportwisdom.domain.reducer.favorite.state.FavoriteSyncState.Loading
    )

    //When
    val newState = com.example.sportwisdom.domain.reducer.favorite.FavoriteReducer().invoke(currentState, action)

    //Then
    Truth.assertThat(newState.syncState).isEqualTo(com.example.sportwisdom.domain.reducer.favorite.state.FavoriteSyncState.Empty)
    Truth.assertThat(newState.favoriteModel).isEqualTo(teams)
  }

  @Test
  fun sideEffect_test() {
    //Given
    val action = com.example.sportwisdom.common.utils.BaseAction.SideEffect(1)
    val teams = flowOf(emptyList<com.example.sportwisdom.domain.model.TeamDto>())
    val currentState = com.example.sportwisdom.domain.reducer.favorite.state.FavoriteState(
      teams,
      com.example.sportwisdom.domain.reducer.favorite.state.FavoriteSyncState.Loading
    )

    //When
    val newState = com.example.sportwisdom.domain.reducer.favorite.FavoriteReducer().invoke(currentState, action)

    //Then
    Truth.assertThat(newState.syncState).isEqualTo(com.example.sportwisdom.domain.reducer.favorite.state.FavoriteSyncState.SideEffect)
    Truth.assertThat(newState.favoriteModel).isEqualTo(teams)
  }
}
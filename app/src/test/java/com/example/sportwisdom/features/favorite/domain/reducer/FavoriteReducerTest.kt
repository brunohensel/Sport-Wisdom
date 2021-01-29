package com.example.sportwisdom.features.favorite.domain.reducer

import com.example.sportwisdom.base.BaseAction
import com.example.sportwisdom.features.favorite.domain.state.FavoriteState
import com.example.sportwisdom.features.favorite.domain.state.FavoriteSyncState
import com.example.sportwisdom.features.search.domain.model.TeamDto
import com.google.common.truth.Truth
import kotlinx.coroutines.flow.flowOf
import org.junit.Test

class FavoriteReducerTest {

  @Test
  fun loading_test() {
    //Given
    val action = BaseAction.Executing
    val teams = flowOf(emptyList<TeamDto>())
    val currentState = FavoriteState(teams, syncState = FavoriteSyncState.Content)

    //When
    val newState = FavoriteReducer().invoke(currentState, action)

    //Then
    Truth.assertThat(newState.syncState).isEqualTo(FavoriteSyncState.Loading)
  }

  @Test
  fun content_test() {
    //Given
    val teams = flowOf(emptyList<TeamDto>())
    val action = BaseAction.CacheSuccess(teams)
    val currentState = FavoriteState(teams, FavoriteSyncState.Loading)

    //When
    val newState = FavoriteReducer().invoke(currentState, action)

    //Then
    Truth.assertThat(newState.syncState).isEqualTo(FavoriteSyncState.Content)
    Truth.assertThat(newState.favoriteModel).isEqualTo(teams)
  }

  @Test
  fun message_test() {
    //Given
    val message = "Error Test"
    val action = BaseAction.Failed(reason = message)
    val teams = flowOf(emptyList<TeamDto>())
    val currentState = FavoriteState(teams, FavoriteSyncState.Loading)

    //When
    val newState = FavoriteReducer().invoke(currentState, action)

    //Then
    Truth.assertThat(newState.syncState).isEqualTo(FavoriteSyncState.Message(message))
    Truth.assertThat(newState.favoriteModel).isEqualTo(teams)
  }

  @Test
  fun empty_test() {
    //Given
    val action = BaseAction.EmptyResult
    val teams = flowOf(emptyList<TeamDto>())
    val currentState = FavoriteState(teams, FavoriteSyncState.Loading)

    //When
    val newState = FavoriteReducer().invoke(currentState, action)

    //Then
    Truth.assertThat(newState.syncState).isEqualTo(FavoriteSyncState.Empty)
    Truth.assertThat(newState.favoriteModel).isEqualTo(teams)
  }

  @Test
  fun sideEffect_test() {
    //Given
    val action = BaseAction.SideEffect(1)
    val teams = flowOf(emptyList<TeamDto>())
    val currentState = FavoriteState(teams, FavoriteSyncState.Loading)

    //When
    val newState = FavoriteReducer().invoke(currentState, action)

    //Then
    Truth.assertThat(newState.syncState).isEqualTo(FavoriteSyncState.SideEffect)
    Truth.assertThat(newState.favoriteModel).isEqualTo(teams)
  }
}
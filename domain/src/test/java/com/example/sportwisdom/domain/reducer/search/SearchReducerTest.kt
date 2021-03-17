package com.example.sportwisdom.domain.reducer.search

import com.example.sportwisdom.common.utils.BaseAction
import com.example.sportwisdom.domain.model.TeamDto
import com.example.sportwisdom.domain.reducer.search.state.SearchState
import com.example.sportwisdom.domain.reducer.search.state.SearchSyncState
import com.google.common.truth.Truth
import org.junit.Test

class SearchReducerTest {
  @Test
  fun loading_test() {
    //Given
    val action = BaseAction.Executing
    val teamDto = emptyList<TeamDto>()
    val currentState = SearchState(
      teamsModel = teamDto,
      syncState = SearchSyncState.Content
    )

    //When
    val newState = SearchReducer().invoke(currentState, action)

    //Then
    Truth.assertThat(newState.syncState).isEqualTo(SearchSyncState.Loading)
  }

  @Test
  fun content_test() {
    //Given
    val teamDto = emptyList<TeamDto>()
    val action = BaseAction.RemoteSuccess(teamDto)
    val currentState = SearchState(
      teamDto,
      SearchSyncState.Loading
    )

    //When
    val newState = SearchReducer().invoke(currentState, action)

    //Then
    Truth.assertThat(newState.syncState).isEqualTo(SearchSyncState.Content)
    Truth.assertThat(newState.teamsModel).isEqualTo(teamDto)
  }

  @Test
  fun message_test() {
    //Given
    val message = "Error Test"
    val action = BaseAction.Failed(reason = message)
    val teamDto = emptyList<TeamDto>()
    val currentState = SearchState(
      teamDto,
      SearchSyncState.Loading
    )

    //When
    val newState = SearchReducer().invoke(currentState, action)

    //Then
    Truth.assertThat(newState.syncState).isEqualTo(SearchSyncState.Message(message))
    Truth.assertThat(newState.teamsModel).isEqualTo(teamDto)
  }

  @Test
  fun empty_test() {
    //Given
    val action = BaseAction.EmptyResult
    val teamDto = emptyList<TeamDto>()
    val currentState = SearchState(
      teamDto,
      SearchSyncState.Loading
    )

    //When
    val newState = SearchReducer().invoke(currentState, action)

    //Then
    Truth.assertThat(newState.syncState).isEqualTo(SearchSyncState.Empty)
    Truth.assertThat(newState.teamsModel).isEqualTo(teamDto)
  }

  @Test
  fun sideEffect_test() {
    //Given
    val action = BaseAction.SideEffect(1)
    val teamDto = emptyList<TeamDto>()
    val currentState = SearchState(
      teamDto,
      SearchSyncState.Loading
    )

    //When
    val newState = SearchReducer().invoke(currentState, action)

    //Then
    Truth.assertThat(newState.syncState).isEqualTo(SearchSyncState.SideEffect)
    Truth.assertThat(newState.teamsModel).isEqualTo(teamDto)
  }
}
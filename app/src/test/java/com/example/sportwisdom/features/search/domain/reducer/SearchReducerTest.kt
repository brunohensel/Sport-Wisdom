package com.example.sportwisdom.features.search.domain.reducer

import com.example.sportwisdom.domain.model.TeamDto
import com.example.sportwisdom.domain.reducer.search.state.SearchState
import com.example.sportwisdom.domain.reducer.search.state.SearchSyncState
import com.google.common.truth.Truth
import org.junit.Test

class SearchReducerTest {
  @Test
  fun loading_test() {
    //Given
    val action = com.example.sportwisdom.common.utils.BaseAction.Executing
    val teamDto = emptyList<com.example.sportwisdom.domain.model.TeamDto>()
    val currentState = com.example.sportwisdom.domain.reducer.search.state.SearchState(
      teamsModel = teamDto,
      syncState = com.example.sportwisdom.domain.reducer.search.state.SearchSyncState.Content
    )

    //When
    val newState = com.example.sportwisdom.domain.reducer.search.SearchReducer().invoke(currentState, action)

    //Then
    Truth.assertThat(newState.syncState).isEqualTo(com.example.sportwisdom.domain.reducer.search.state.SearchSyncState.Loading)
  }

  @Test
  fun content_test() {
    //Given
    val teamDto = emptyList<com.example.sportwisdom.domain.model.TeamDto>()
    val action = com.example.sportwisdom.common.utils.BaseAction.RemoteSuccess(teamDto)
    val currentState = com.example.sportwisdom.domain.reducer.search.state.SearchState(
      teamDto,
      com.example.sportwisdom.domain.reducer.search.state.SearchSyncState.Loading
    )

    //When
    val newState = com.example.sportwisdom.domain.reducer.search.SearchReducer().invoke(currentState, action)

    //Then
    Truth.assertThat(newState.syncState).isEqualTo(com.example.sportwisdom.domain.reducer.search.state.SearchSyncState.Content)
    Truth.assertThat(newState.teamsModel).isEqualTo(teamDto)
  }

  @Test
  fun message_test() {
    //Given
    val message = "Error Test"
    val action = com.example.sportwisdom.common.utils.BaseAction.Failed(reason = message)
    val teamDto = emptyList<com.example.sportwisdom.domain.model.TeamDto>()
    val currentState = com.example.sportwisdom.domain.reducer.search.state.SearchState(
      teamDto,
      com.example.sportwisdom.domain.reducer.search.state.SearchSyncState.Loading
    )

    //When
    val newState = com.example.sportwisdom.domain.reducer.search.SearchReducer().invoke(currentState, action)

    //Then
    Truth.assertThat(newState.syncState).isEqualTo(com.example.sportwisdom.domain.reducer.search.state.SearchSyncState.Message(message))
    Truth.assertThat(newState.teamsModel).isEqualTo(teamDto)
  }

  @Test
  fun empty_test() {
    //Given
    val action = com.example.sportwisdom.common.utils.BaseAction.EmptyResult
    val teamDto = emptyList<com.example.sportwisdom.domain.model.TeamDto>()
    val currentState = com.example.sportwisdom.domain.reducer.search.state.SearchState(
      teamDto,
      com.example.sportwisdom.domain.reducer.search.state.SearchSyncState.Loading
    )

    //When
    val newState = com.example.sportwisdom.domain.reducer.search.SearchReducer().invoke(currentState, action)

    //Then
    Truth.assertThat(newState.syncState).isEqualTo(com.example.sportwisdom.domain.reducer.search.state.SearchSyncState.Empty)
    Truth.assertThat(newState.teamsModel).isEqualTo(teamDto)
  }

  @Test
  fun sideEffect_test() {
    //Given
    val action = com.example.sportwisdom.common.utils.BaseAction.SideEffect(1)
    val teamDto = emptyList<com.example.sportwisdom.domain.model.TeamDto>()
    val currentState = com.example.sportwisdom.domain.reducer.search.state.SearchState(
      teamDto,
      com.example.sportwisdom.domain.reducer.search.state.SearchSyncState.Loading
    )

    //When
    val newState = com.example.sportwisdom.domain.reducer.search.SearchReducer().invoke(currentState, action)

    //Then
    Truth.assertThat(newState.syncState).isEqualTo(com.example.sportwisdom.domain.reducer.search.state.SearchSyncState.SideEffect)
    Truth.assertThat(newState.teamsModel).isEqualTo(teamDto)
  }
}
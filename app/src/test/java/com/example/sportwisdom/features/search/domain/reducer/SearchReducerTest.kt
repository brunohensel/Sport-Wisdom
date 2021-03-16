package com.example.sportwisdom.features.search.domain.reducer

import com.example.sportwisdom.common.utils.BaseAction
import com.example.sportwisdom.features.search.domain.model.TeamDto
import com.example.sportwisdom.features.search.domain.state.SearchState
import com.example.sportwisdom.features.search.domain.state.SearchSyncState
import com.google.common.truth.Truth
import org.junit.Test

class SearchReducerTest {
  @Test
  fun loading_test() {
    //Given
    val action = com.example.sportwisdom.common.utils.BaseAction.Executing
    val teamDto = emptyList<TeamDto>()
    val currentState = SearchState(teamsModel = teamDto, syncState = SearchSyncState.Content)

    //When
    val newState = SearchReducer().invoke(currentState, action)

    //Then
    Truth.assertThat(newState.syncState).isEqualTo(SearchSyncState.Loading)
  }

  @Test
  fun content_test() {
    //Given
    val teamDto = emptyList<TeamDto>()
    val action = com.example.sportwisdom.common.utils.BaseAction.RemoteSuccess(teamDto)
    val currentState = SearchState(teamDto, SearchSyncState.Loading)

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
    val action = com.example.sportwisdom.common.utils.BaseAction.Failed(reason = message)
    val teamDto = emptyList<TeamDto>()
    val currentState = SearchState(teamDto, SearchSyncState.Loading)

    //When
    val newState = SearchReducer().invoke(currentState, action)

    //Then
    Truth.assertThat(newState.syncState).isEqualTo(SearchSyncState.Message(message))
    Truth.assertThat(newState.teamsModel).isEqualTo(teamDto)
  }

  @Test
  fun empty_test() {
    //Given
    val action = com.example.sportwisdom.common.utils.BaseAction.EmptyResult
    val teamDto = emptyList<TeamDto>()
    val currentState = SearchState(teamDto, SearchSyncState.Loading)

    //When
    val newState = SearchReducer().invoke(currentState, action)

    //Then
    Truth.assertThat(newState.syncState).isEqualTo(SearchSyncState.Empty)
    Truth.assertThat(newState.teamsModel).isEqualTo(teamDto)
  }

  @Test
  fun sideEffect_test() {
    //Given
    val action = com.example.sportwisdom.common.utils.BaseAction.SideEffect(1)
    val teamDto = emptyList<TeamDto>()
    val currentState = SearchState(teamDto, SearchSyncState.Loading)

    //When
    val newState = SearchReducer().invoke(currentState, action)

    //Then
    Truth.assertThat(newState.syncState).isEqualTo(SearchSyncState.SideEffect)
    Truth.assertThat(newState.teamsModel).isEqualTo(teamDto)
  }
}
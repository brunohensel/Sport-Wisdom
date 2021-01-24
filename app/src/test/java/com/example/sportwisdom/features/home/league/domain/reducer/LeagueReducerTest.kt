package com.example.sportwisdom.features.home.league.domain.reducer

import com.example.sportwisdom.base.BaseAction
import com.example.sportwisdom.features.home.league.domain.model.LeagueDto
import com.example.sportwisdom.features.home.league.domain.state.LeagueState
import com.example.sportwisdom.features.home.league.domain.state.LeagueSyncState
import com.google.common.truth.Truth
import org.junit.Test

class LeagueReducerTest {

  @Test
  fun loading_test() {
    //Given
    val action = BaseAction.Executing
    val leagues = emptyList<LeagueDto>()
    val currentState = LeagueState(leagueModel = leagues, syncState = LeagueSyncState.Content)

    //When
    val newState = LeagueReducer().invoke(currentState, action)

    //Then
    Truth.assertThat(newState.syncState).isEqualTo(LeagueSyncState.Loading)
  }

  @Test
  fun content_test() {
    //Given
    val leagues = emptyList<LeagueDto>()
    val action = BaseAction.RemoteSuccess(leagues)
    val currentState = LeagueState(leagues, LeagueSyncState.Loading)

    //When
    val newState = LeagueReducer().invoke(currentState, action)

    //Then
    Truth.assertThat(newState.syncState).isEqualTo(LeagueSyncState.Content)
    Truth.assertThat(newState.leagueModel).isEqualTo(leagues)
  }

  @Test
  fun message_test() {
    //Given
    val message = "Error Test"
    val action = BaseAction.Failed(reason = message)
    val leagues = emptyList<LeagueDto>()
    val currentState = LeagueState(leagues, LeagueSyncState.Loading)

    //When
    val newState = LeagueReducer().invoke(currentState, action)

    //Then
    Truth.assertThat(newState.syncState).isEqualTo(LeagueSyncState.Message(message))
    Truth.assertThat(newState.leagueModel).isEqualTo(leagues)
  }

  @Test
  fun empty_test() {
    //Given
    val action = BaseAction.EmptyResult
    val leagues = emptyList<LeagueDto>()
    val currentState = LeagueState(leagues, LeagueSyncState.Loading)

    //When
    val newState = LeagueReducer().invoke(currentState, action)

    //Then
    Truth.assertThat(newState.syncState).isEqualTo(LeagueSyncState.Empty)
    Truth.assertThat(newState.leagueModel).isEqualTo(leagues)
  }
}
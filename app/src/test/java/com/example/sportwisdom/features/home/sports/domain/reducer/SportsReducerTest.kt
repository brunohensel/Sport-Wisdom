package com.example.sportwisdom.features.home.sports.domain.reducer

import com.example.sportwisdom.domain.model.SportsModel
import com.example.sportwisdom.domain.reducer.sports.state.SportState
import com.example.sportwisdom.domain.reducer.sports.state.SportSyncState
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class SportsReducerTest {

  @Test
  fun loading_test() {
    //Given
    val action = com.example.sportwisdom.common.utils.BaseAction.Executing
    val sports = com.example.sportwisdom.domain.model.SportsModel(emptyList())
    val currentState = SportState(sportsModel = sports, syncState = SportSyncState.Content)

    //When
    val newState = com.example.sportwisdom.domain.reducer.sports.SportsReducer().invoke(currentState, action)

    //Then
    assertThat(newState.syncState).isEqualTo(SportSyncState.Loading)
  }

  @Test
  fun content_test(){
    //Given
    val sports = com.example.sportwisdom.domain.model.SportsModel(emptyList())
    val action = com.example.sportwisdom.common.utils.BaseAction.RemoteSuccess(sports)
    val currentState = SportState(sports, SportSyncState.Loading)

    //When
    val newState = com.example.sportwisdom.domain.reducer.sports.SportsReducer().invoke(currentState, action)

    //Then
    assertThat(newState.syncState).isEqualTo(SportSyncState.Content)
    assertThat(newState.sportsModel.sports).isEqualTo(sports.sports)
  }

  @Test
  fun message_test() {
    //Given
    val message = "Error Test"
    val action = com.example.sportwisdom.common.utils.BaseAction.Failed(reason = message)
    val sports = com.example.sportwisdom.domain.model.SportsModel(emptyList())
    val currentState = SportState(sports, SportSyncState.Loading)

    //When
    val newState = com.example.sportwisdom.domain.reducer.sports.SportsReducer().invoke(currentState, action)

    //Then
    assertThat(newState.syncState).isEqualTo(SportSyncState.Message(message))
    assertThat(newState.sportsModel.sports).isEqualTo(sports.sports)
  }

  @Test
  fun empty_test() {
    //Given
    val action = com.example.sportwisdom.common.utils.BaseAction.EmptyResult
    val sports = com.example.sportwisdom.domain.model.SportsModel(emptyList())
    val currentState = SportState(sports, SportSyncState.Loading)

    //When
    val newState = com.example.sportwisdom.domain.reducer.sports.SportsReducer().invoke(currentState, action)

    //Then
    assertThat(newState.syncState).isEqualTo(SportSyncState.Empty)
    assertThat(newState.sportsModel.sports).isEqualTo(sports.sports)
  }
}
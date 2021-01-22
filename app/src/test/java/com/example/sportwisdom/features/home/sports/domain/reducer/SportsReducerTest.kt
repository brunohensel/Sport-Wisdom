package com.example.sportwisdom.features.home.sports.domain.reducer

import com.example.sportwisdom.base.BaseAction
import com.example.sportwisdom.features.home.sports.domain.model.SportsModel
import com.example.sportwisdom.features.home.sports.domain.state.HomeState
import com.example.sportwisdom.features.home.sports.domain.state.HomeSyncState
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class SportsReducerTest {

  @Test
  fun loading_test() {
    //Given
    val action = BaseAction.Executing
    val sports = SportsModel(emptyList())
    val currentState = HomeState(sportsModel = sports, syncState = HomeSyncState.Content)

    //When
    val newState = SportsReducer().invoke(currentState, action)

    //Then
    assertThat(newState.syncState).isEqualTo(HomeSyncState.Loading)
  }

  @Test
  fun content_test(){
    //Given
    val sports = SportsModel(emptyList())
    val action = BaseAction.Success(sports)
    val currentState = HomeState(sports, HomeSyncState.Loading)

    //When
    val newState = SportsReducer().invoke(currentState, action)

    //Then
    assertThat(newState.syncState).isEqualTo(HomeSyncState.Content)
    assertThat(newState.sportsModel.sports).isEqualTo(sports.sports)
  }
}
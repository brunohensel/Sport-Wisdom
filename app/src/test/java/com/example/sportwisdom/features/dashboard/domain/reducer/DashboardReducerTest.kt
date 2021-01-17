package com.example.sportwisdom.features.dashboard.domain.reducer

import com.example.sportwisdom.base.BaseAction
import com.example.sportwisdom.features.dashboard.domain.model.League
import com.example.sportwisdom.features.dashboard.domain.state.DashboardState
import com.example.sportwisdom.features.dashboard.domain.state.DashboardSyncState
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class DashboardReducerTest {

  @Test
  fun loading_test() {
    //Given
    val action = BaseAction.Executing
    val map = mapOf("Soccer" to listOf<League>())
    val currentState = DashboardState(sportsModel = map, syncState = DashboardSyncState.Content)

    //When
    val newState = DashboardReducer().invoke(currentState, action)

    //Then
    assertThat(newState.syncState).isEqualTo(DashboardSyncState.Loading)
  }

  @Test
  fun content_test(){
    //Given
    val map = mapOf("Soccer" to listOf<League>())
    val action = BaseAction.Success(map)
    val currentState = DashboardState(map, DashboardSyncState.Loading)

    //When
    val newState = DashboardReducer().invoke(currentState, action)

    //Then
    assertThat(newState.syncState).isEqualTo(DashboardSyncState.Content)
    assertThat(newState.sportsModel.values).isEqualTo(map.values)
  }
}
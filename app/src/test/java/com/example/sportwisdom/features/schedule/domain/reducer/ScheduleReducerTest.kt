package com.example.sportwisdom.features.schedule.domain.reducer

import com.example.sportwisdom.domain.model.EventDto
import com.example.sportwisdom.domain.reducer.schedule.state.ScheduleState
import com.example.sportwisdom.domain.reducer.schedule.state.ScheduleSyncState
import com.google.common.truth.Truth
import kotlinx.coroutines.flow.flowOf
import org.junit.Test

class ScheduleReducerTest {

  @Test
  fun loading_test() {
    //Given
    val action = com.example.sportwisdom.common.utils.BaseAction.Executing
    val eventDto = flowOf(emptyList<com.example.sportwisdom.domain.model.EventDto>())
    val currentState = com.example.sportwisdom.domain.reducer.schedule.state.ScheduleState(
      scheduleModel = eventDto,
      syncState = com.example.sportwisdom.domain.reducer.schedule.state.ScheduleSyncState.Content
    )

    //When
    val newState = com.example.sportwisdom.domain.reducer.schedule.ScheduleReducer().invoke(currentState, action)

    //Then
    Truth.assertThat(newState.syncState).isEqualTo(com.example.sportwisdom.domain.reducer.schedule.state.ScheduleSyncState.Loading)
  }

  @Test
  fun content_test() {
    //Given
    val eventDto = flowOf(emptyList<com.example.sportwisdom.domain.model.EventDto>())
    val action = com.example.sportwisdom.common.utils.BaseAction.CacheSuccess(eventDto)
    val currentState = com.example.sportwisdom.domain.reducer.schedule.state.ScheduleState(
      eventDto,
      com.example.sportwisdom.domain.reducer.schedule.state.ScheduleSyncState.Loading
    )

    //When
    val newState = com.example.sportwisdom.domain.reducer.schedule.ScheduleReducer().invoke(currentState, action)

    //Then
    Truth.assertThat(newState.syncState).isEqualTo(com.example.sportwisdom.domain.reducer.schedule.state.ScheduleSyncState.Content)
    Truth.assertThat(newState.scheduleModel).isEqualTo(eventDto)
  }

  @Test
  fun message_test() {
    //Given
    val message = "Error Test"
    val action = com.example.sportwisdom.common.utils.BaseAction.Failed(reason = message)
    val eventDto = flowOf(emptyList<com.example.sportwisdom.domain.model.EventDto>())
    val currentState = com.example.sportwisdom.domain.reducer.schedule.state.ScheduleState(
      eventDto,
      com.example.sportwisdom.domain.reducer.schedule.state.ScheduleSyncState.Loading
    )

    //When
    val newState = com.example.sportwisdom.domain.reducer.schedule.ScheduleReducer().invoke(currentState, action)

    //Then
    Truth.assertThat(newState.syncState).isEqualTo(com.example.sportwisdom.domain.reducer.schedule.state.ScheduleSyncState.Message(message))
    Truth.assertThat(newState.scheduleModel).isEqualTo(eventDto)
  }

  @Test
  fun empty_test() {
    //Given
    val action = com.example.sportwisdom.common.utils.BaseAction.EmptyResult
    val eventDto = flowOf(emptyList<com.example.sportwisdom.domain.model.EventDto>())
    val currentState = com.example.sportwisdom.domain.reducer.schedule.state.ScheduleState(
      eventDto,
      com.example.sportwisdom.domain.reducer.schedule.state.ScheduleSyncState.Loading
    )

    //When
    val newState = com.example.sportwisdom.domain.reducer.schedule.ScheduleReducer().invoke(currentState, action)

    //Then
    Truth.assertThat(newState.syncState).isEqualTo(com.example.sportwisdom.domain.reducer.schedule.state.ScheduleSyncState.Empty)
    Truth.assertThat(newState.scheduleModel).isEqualTo(eventDto)
  }

  @Test
  fun sideEffect_test() {
    //Given
    val action = com.example.sportwisdom.common.utils.BaseAction.SideEffect(1)
    val eventDto = flowOf(emptyList<com.example.sportwisdom.domain.model.EventDto>())
    val currentState = com.example.sportwisdom.domain.reducer.schedule.state.ScheduleState(
      eventDto,
      com.example.sportwisdom.domain.reducer.schedule.state.ScheduleSyncState.Loading
    )

    //When
    val newState = com.example.sportwisdom.domain.reducer.schedule.ScheduleReducer().invoke(currentState, action)

    //Then
    Truth.assertThat(newState.syncState).isEqualTo(com.example.sportwisdom.domain.reducer.schedule.state.ScheduleSyncState.SideEffect)
    Truth.assertThat(newState.scheduleModel).isEqualTo(eventDto)
  }
}
package com.example.sportwisdom.features.schedule.domain.reducer

import com.example.sportwisdom.base.BaseAction
import com.example.sportwisdom.features.home.events.domain.model.EventDto
import com.example.sportwisdom.features.schedule.domain.state.ScheduleState
import com.example.sportwisdom.features.schedule.domain.state.ScheduleSyncState
import com.google.common.truth.Truth
import kotlinx.coroutines.flow.flowOf
import org.junit.Test

class ScheduleReducerTest {

  @Test
  fun loading_test() {
    //Given
    val action = BaseAction.Executing
    val eventDto = flowOf(emptyList<EventDto>())
    val currentState = ScheduleState(scheduleModel = eventDto, syncState = ScheduleSyncState.Content)

    //When
    val newState = ScheduleReducer().invoke(currentState, action)

    //Then
    Truth.assertThat(newState.syncState).isEqualTo(ScheduleSyncState.Loading)
  }

  @Test
  fun content_test() {
    //Given
    val eventDto = flowOf(emptyList<EventDto>())
    val action = BaseAction.CacheSuccess(eventDto)
    val currentState = ScheduleState(eventDto, ScheduleSyncState.Loading)

    //When
    val newState = ScheduleReducer().invoke(currentState, action)

    //Then
    Truth.assertThat(newState.syncState).isEqualTo(ScheduleSyncState.Content)
    Truth.assertThat(newState.scheduleModel).isEqualTo(eventDto)
  }

  @Test
  fun message_test() {
    //Given
    val message = "Error Test"
    val action = BaseAction.Failed(reason = message)
    val eventDto = flowOf(emptyList<EventDto>())
    val currentState = ScheduleState(eventDto, ScheduleSyncState.Loading)

    //When
    val newState = ScheduleReducer().invoke(currentState, action)

    //Then
    Truth.assertThat(newState.syncState).isEqualTo(ScheduleSyncState.Message(message))
    Truth.assertThat(newState.scheduleModel).isEqualTo(eventDto)
  }

  @Test
  fun empty_test() {
    //Given
    val action = BaseAction.EmptyResult
    val eventDto = flowOf(emptyList<EventDto>())
    val currentState = ScheduleState(eventDto, ScheduleSyncState.Loading)

    //When
    val newState = ScheduleReducer().invoke(currentState, action)

    //Then
    Truth.assertThat(newState.syncState).isEqualTo(ScheduleSyncState.Empty)
    Truth.assertThat(newState.scheduleModel).isEqualTo(eventDto)
  }

  @Test
  fun sideEffect_test() {
    //Given
    val action = BaseAction.SideEffect(1)
    val eventDto = flowOf(emptyList<EventDto>())
    val currentState = ScheduleState(eventDto, ScheduleSyncState.Loading)

    //When
    val newState = ScheduleReducer().invoke(currentState, action)

    //Then
    Truth.assertThat(newState.syncState).isEqualTo(ScheduleSyncState.SideEffect)
    Truth.assertThat(newState.scheduleModel).isEqualTo(eventDto)
  }
}
package com.example.sportwisdom.features.home.events.domain.reducer

import com.example.sportwisdom.domain.model.EventDto
import com.example.sportwisdom.domain.reducer.events.state.EventSyncState
import com.example.sportwisdom.domain.reducer.events.state.EventsState
import com.google.common.truth.Truth
import org.junit.Test

class EventsReducerTest {
  @Test
  fun loading_test() {
    //Given
    val action = com.example.sportwisdom.common.utils.BaseAction.Executing
    val events = emptyList<com.example.sportwisdom.domain.model.EventDto>()
    val currentState = com.example.sportwisdom.domain.reducer.events.state.EventsState(
      eventsModel = events,
      syncState = com.example.sportwisdom.domain.reducer.events.state.EventSyncState.Content
    )

    //When
    val newState = com.example.sportwisdom.domain.reducer.events.EventsReducer().invoke(currentState, action)

    //Then
    Truth.assertThat(newState.syncState).isEqualTo(com.example.sportwisdom.domain.reducer.events.state.EventSyncState.Loading)
  }

  @Test
  fun content_test() {
    //Given
    val events = emptyList<com.example.sportwisdom.domain.model.EventDto>()
    val action = com.example.sportwisdom.common.utils.BaseAction.RemoteSuccess(events)
    val currentState = com.example.sportwisdom.domain.reducer.events.state.EventsState(
      events,
      com.example.sportwisdom.domain.reducer.events.state.EventSyncState.Loading
    )

    //When
    val newState = com.example.sportwisdom.domain.reducer.events.EventsReducer().invoke(currentState, action)

    //Then
    Truth.assertThat(newState.syncState).isEqualTo(com.example.sportwisdom.domain.reducer.events.state.EventSyncState.Content)
    Truth.assertThat(newState.eventsModel).isEqualTo(events)
  }

  @Test
  fun message_test() {
    //Given
    val message = "Error Test"
    val action = com.example.sportwisdom.common.utils.BaseAction.Failed(reason = message)
    val events = emptyList<com.example.sportwisdom.domain.model.EventDto>()
    val currentState = com.example.sportwisdom.domain.reducer.events.state.EventsState(
      events,
      com.example.sportwisdom.domain.reducer.events.state.EventSyncState.Loading
    )

    //When
    val newState = com.example.sportwisdom.domain.reducer.events.EventsReducer().invoke(currentState, action)

    //Then
    Truth.assertThat(newState.syncState).isEqualTo(com.example.sportwisdom.domain.reducer.events.state.EventSyncState.Message(message))
    Truth.assertThat(newState.eventsModel).isEqualTo(events)
  }

  @Test
  fun empty_test() {
    //Given
    val action = com.example.sportwisdom.common.utils.BaseAction.EmptyResult
    val events = emptyList<com.example.sportwisdom.domain.model.EventDto>()
    val currentState = com.example.sportwisdom.domain.reducer.events.state.EventsState(
      events,
      com.example.sportwisdom.domain.reducer.events.state.EventSyncState.Empty
    )

    //When
    val newState = com.example.sportwisdom.domain.reducer.events.EventsReducer().invoke(currentState, action)

    //Then
    Truth.assertThat(newState.syncState).isEqualTo(com.example.sportwisdom.domain.reducer.events.state.EventSyncState.Empty)
    Truth.assertThat(newState.eventsModel).isEqualTo(events)
  }
}
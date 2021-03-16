package com.example.sportwisdom.features.home.events.domain.reducer

import com.example.sportwisdom.common.utils.BaseAction
import com.example.sportwisdom.features.home.events.domain.model.EventDto
import com.example.sportwisdom.features.home.events.domain.state.EventSyncState
import com.example.sportwisdom.features.home.events.domain.state.EventsState
import com.google.common.truth.Truth
import org.junit.Test

class EventsReducerTest {
  @Test
  fun loading_test() {
    //Given
    val action = com.example.sportwisdom.common.utils.BaseAction.Executing
    val events = emptyList<EventDto>()
    val currentState = EventsState(eventsModel = events, syncState = EventSyncState.Content)

    //When
    val newState = EventsReducer().invoke(currentState, action)

    //Then
    Truth.assertThat(newState.syncState).isEqualTo(EventSyncState.Loading)
  }

  @Test
  fun content_test() {
    //Given
    val events = emptyList<EventDto>()
    val action = com.example.sportwisdom.common.utils.BaseAction.RemoteSuccess(events)
    val currentState = EventsState(events, EventSyncState.Loading)

    //When
    val newState = EventsReducer().invoke(currentState, action)

    //Then
    Truth.assertThat(newState.syncState).isEqualTo(EventSyncState.Content)
    Truth.assertThat(newState.eventsModel).isEqualTo(events)
  }

  @Test
  fun message_test() {
    //Given
    val message = "Error Test"
    val action = com.example.sportwisdom.common.utils.BaseAction.Failed(reason = message)
    val events = emptyList<EventDto>()
    val currentState = EventsState(events, EventSyncState.Loading)

    //When
    val newState = EventsReducer().invoke(currentState, action)

    //Then
    Truth.assertThat(newState.syncState).isEqualTo(EventSyncState.Message(message))
    Truth.assertThat(newState.eventsModel).isEqualTo(events)
  }

  @Test
  fun empty_test() {
    //Given
    val action = com.example.sportwisdom.common.utils.BaseAction.EmptyResult
    val events = emptyList<EventDto>()
    val currentState = EventsState(events, EventSyncState.Empty)

    //When
    val newState = EventsReducer().invoke(currentState, action)

    //Then
    Truth.assertThat(newState.syncState).isEqualTo(EventSyncState.Empty)
    Truth.assertThat(newState.eventsModel).isEqualTo(events)
  }
}
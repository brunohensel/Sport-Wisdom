package com.example.sportwisdom.domain.reducer.events

import com.example.sportwisdom.common.utils.BaseAction
import com.example.sportwisdom.domain.model.EventDto
import com.example.sportwisdom.domain.reducer.events.state.EventSyncState
import com.example.sportwisdom.domain.reducer.events.state.EventsState
import com.google.common.truth.Truth
import org.junit.Test

class EventsReducerTest {
  @Test
  fun loading_test() {
    //Given
    val action = BaseAction.Executing
    val events = emptyList<EventDto>()
    val currentState = EventsState(
      eventsModel = events,
      syncState = EventSyncState.Content
    )

    //When
    val newState = EventsReducer().invoke(currentState, action)

    //Then
    Truth.assertThat(newState.syncState).isEqualTo(EventSyncState.Loading)
  }

  @Test
  fun content_test() {
    //Given
    val events = emptyList<EventDto>()
    val action = BaseAction.RemoteSuccess(events)
    val currentState = EventsState(
      events,
      EventSyncState.Loading
    )

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
    val action = BaseAction.Failed(reason = message)
    val events = emptyList<EventDto>()
    val currentState = EventsState(
      events,
      EventSyncState.Loading
    )

    //When
    val newState = EventsReducer().invoke(currentState, action)

    //Then
    Truth.assertThat(newState.syncState).isEqualTo(EventSyncState.Message(message))
    Truth.assertThat(newState.eventsModel).isEqualTo(events)
  }

  @Test
  fun empty_test() {
    //Given
    val action = BaseAction.EmptyResult
    val events = emptyList<EventDto>()
    val currentState = EventsState(
      events,
      EventSyncState.Empty
    )

    //When
    val newState = EventsReducer().invoke(currentState, action)

    //Then
    Truth.assertThat(newState.syncState).isEqualTo(EventSyncState.Empty)
    Truth.assertThat(newState.eventsModel).isEqualTo(events)
  }
}
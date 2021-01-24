package com.example.sportwisdom.features.home.events.domain.state

import com.example.sportwisdom.features.home.events.domain.model.EventDto

data class EventsState(
  val eventsModel: List<EventDto>,
  val syncState: EventSyncState
)

sealed class EventSyncState {
  object Loading : EventSyncState()
  object Content : EventSyncState()
  object Cache   : EventSyncState()
  object Empty : EventSyncState()
  data class Message(val msg: String?) : EventSyncState()
}

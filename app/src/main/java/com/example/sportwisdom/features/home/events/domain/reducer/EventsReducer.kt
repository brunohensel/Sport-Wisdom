package com.example.sportwisdom.features.home.events.domain.reducer

import com.example.redux.Reducer
import com.example.sportwisdom.base.BaseAction
import com.example.sportwisdom.features.home.events.domain.model.EventDto
import com.example.sportwisdom.features.home.events.domain.state.EventSyncState
import com.example.sportwisdom.features.home.events.domain.state.EventsState
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class EventsReducer @Inject constructor() : Reducer<EventsState, BaseAction> {
  override fun invoke(currentState: EventsState, action: BaseAction): EventsState {
    return when (action) {
      BaseAction.Executing -> currentState.copy(syncState = EventSyncState.Loading)
      is BaseAction.Success<*> -> currentState.copy(eventsModel = action.value as List<EventDto>? ?: emptyList(), syncState = EventSyncState.Content)
      is BaseAction.Failed -> currentState.copy(syncState = EventSyncState.Message(action.reason))
    }
  }
}
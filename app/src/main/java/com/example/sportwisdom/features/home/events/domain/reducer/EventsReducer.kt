package com.example.sportwisdom.features.home.events.domain.reducer

import com.example.redux.Reducer
import com.example.sportwisdom.common.utils.BaseAction
import com.example.sportwisdom.features.home.events.domain.model.EventDto
import com.example.sportwisdom.features.home.events.domain.state.EventSyncState
import com.example.sportwisdom.features.home.events.domain.state.EventsState
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class EventsReducer @Inject constructor() : Reducer<EventsState, com.example.sportwisdom.common.utils.BaseAction<*>> {
  override fun invoke(currentState: EventsState, action: com.example.sportwisdom.common.utils.BaseAction<*>): EventsState {
    return when (action) {
      com.example.sportwisdom.common.utils.BaseAction.Executing           -> currentState.copy(syncState = EventSyncState.Loading)
      com.example.sportwisdom.common.utils.BaseAction.EmptyResult         -> currentState.copy(syncState = EventSyncState.Empty)
      is com.example.sportwisdom.common.utils.BaseAction.RemoteSuccess<*> -> currentState.copy(eventsModel = action.value as List<EventDto>, syncState = EventSyncState.Content)
      is com.example.sportwisdom.common.utils.BaseAction.Failed           -> currentState.copy(syncState = EventSyncState.Message(action.reason))
      is com.example.sportwisdom.common.utils.BaseAction.CacheSuccess<*>  -> currentState.copy(syncState = EventSyncState.Cache)
      else                                                                -> throw IllegalStateException("Wrong Action: $action for this Reducer: ${javaClass.simpleName}")
    }
  }
}
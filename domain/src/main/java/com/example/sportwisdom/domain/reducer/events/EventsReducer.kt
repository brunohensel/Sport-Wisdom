package com.example.sportwisdom.domain.reducer.events

import com.example.redux.Reducer
import com.example.sportwisdom.common.utils.BaseAction
import com.example.sportwisdom.domain.model.EventDto
import com.example.sportwisdom.domain.reducer.events.state.EventSyncState
import com.example.sportwisdom.domain.reducer.events.state.EventSyncState.*
import com.example.sportwisdom.domain.reducer.events.state.EventsState
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class EventsReducer @Inject constructor() : Reducer<EventsState, BaseAction<*>> {
  override fun invoke(currentState: EventsState, action: BaseAction<*>): EventsState {
    return when (action) {
      BaseAction.Executing           -> currentState.copy(syncState = Loading)
      BaseAction.EmptyResult         -> currentState.copy(syncState = Empty)
      is BaseAction.RemoteSuccess<*> -> currentState.copy(eventsModel = action.value as List<EventDto>, syncState = Content)
      is BaseAction.Failed           -> currentState.copy(syncState = Message(action.reason))
      is BaseAction.CacheSuccess<*>  -> currentState.copy(syncState = Cache)
      else                           -> throw IllegalStateException("Wrong Action: $action for this Reducer: ${javaClass.simpleName}")
    }
  }
}
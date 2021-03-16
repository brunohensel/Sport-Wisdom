package com.example.sportwisdom.features.home.events.presentation

import androidx.hilt.lifecycle.ViewModelInject
import com.example.sportwisdom.common.utils.BaseAction
import com.example.sportwisdom.common.utils.BaseStateViewModel
import com.example.sportwisdom.domain.reducer.home.HomeActionCreator
import com.example.sportwisdom.domain.reducer.home.HomeIntents
import com.example.sportwisdom.domain.reducer.events.EventsReducer
import com.example.sportwisdom.domain.reducer.events.state.EventSyncState
import com.example.sportwisdom.domain.reducer.events.state.EventsState
import kotlinx.coroutines.FlowPreview

@FlowPreview
class EventsViewModel @ViewModelInject constructor(
  reducer: EventsReducer,
  actionCreator: HomeActionCreator
) : BaseStateViewModel<EventsState, HomeIntents, BaseAction<*>>(
  initialState = EventsState(
    eventsModel = emptyList(),
    syncState = EventSyncState.Content
  ),
  reducer = reducer,
  action = actionCreator
)
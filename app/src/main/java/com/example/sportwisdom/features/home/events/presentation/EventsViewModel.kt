package com.example.sportwisdom.features.home.events.presentation

import androidx.hilt.lifecycle.ViewModelInject
import com.example.sportwisdom.base.BaseAction
import com.example.sportwisdom.base.BaseStateViewModel
import com.example.sportwisdom.features.home.domain.HomeActionCreator
import com.example.sportwisdom.features.home.domain.HomeEvent
import com.example.sportwisdom.features.home.events.domain.reducer.EventsReducer
import com.example.sportwisdom.features.home.events.domain.state.EventSyncState
import com.example.sportwisdom.features.home.events.domain.state.EventsState
import kotlinx.coroutines.FlowPreview

@FlowPreview
class EventsViewModel @ViewModelInject constructor(
  reducer: EventsReducer,
  actionCreator: HomeActionCreator
) : BaseStateViewModel<EventsState, HomeEvent, BaseAction>(
  initialState = EventsState(eventsModel = emptyList(), syncState = EventSyncState.Content),
  reducer = reducer,
  action = actionCreator
)
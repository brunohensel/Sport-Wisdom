package com.example.sportwisdom.features.home.events.presentation

import androidx.hilt.lifecycle.ViewModelInject
import com.example.sportwisdom.common.utils.BaseAction
import com.example.sportwisdom.common.utils.BaseStateViewModel
import com.example.sportwisdom.features.home.domain.HomeActionCreator
import com.example.sportwisdom.features.home.domain.HomeIntents
import com.example.sportwisdom.features.home.events.domain.reducer.EventsReducer
import com.example.sportwisdom.features.home.events.domain.state.EventSyncState
import com.example.sportwisdom.features.home.events.domain.state.EventsState
import kotlinx.coroutines.FlowPreview

@FlowPreview
class EventsViewModel @ViewModelInject constructor(
  reducer: EventsReducer,
  actionCreator: HomeActionCreator
) : com.example.sportwisdom.common.utils.BaseStateViewModel<EventsState, HomeIntents, com.example.sportwisdom.common.utils.BaseAction<*>>(
  initialState = EventsState(eventsModel = emptyList(), syncState = EventSyncState.Content),
  reducer = reducer,
  action = actionCreator
)
package com.example.sportwisdom.features.home.sports.presentation

import androidx.hilt.lifecycle.ViewModelInject
import com.example.sportwisdom.base.BaseAction
import com.example.sportwisdom.base.BaseStateViewModel
import com.example.sportwisdom.features.home.sports.domain.model.SportsModel
import com.example.sportwisdom.features.home.domain.HomeActionCreator
import com.example.sportwisdom.features.home.domain.HomeEvent
import com.example.sportwisdom.features.home.sports.domain.reducer.SportsReducer
import com.example.sportwisdom.features.home.sports.domain.state.HomeState
import com.example.sportwisdom.features.home.sports.domain.state.HomeSyncState
import kotlinx.coroutines.FlowPreview

@FlowPreview
class HomeViewModel @ViewModelInject constructor(
  reducer: SportsReducer,
  actionCreator: HomeActionCreator
): BaseStateViewModel<HomeState, HomeEvent, BaseAction>(
  initialState = HomeState(sportsModel = SportsModel(emptyList()), syncState = HomeSyncState.Content),
  reducer = reducer,
  action = actionCreator
)
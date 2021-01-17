package com.example.sportwisdom.features.dashboard.presentation

import androidx.hilt.lifecycle.ViewModelInject
import com.example.sportwisdom.base.BaseAction
import com.example.sportwisdom.base.BaseStateViewModel
import com.example.sportwisdom.features.dashboard.domain.model.SportsModel
import com.example.sportwisdom.features.dashboard.domain.reducer.DashboardActionCreator
import com.example.sportwisdom.features.dashboard.domain.reducer.DashboardEvent
import com.example.sportwisdom.features.dashboard.domain.reducer.DashboardReducer
import com.example.sportwisdom.features.dashboard.domain.state.DashboardState
import com.example.sportwisdom.features.dashboard.domain.state.DashboardSyncState
import kotlinx.coroutines.FlowPreview

@FlowPreview
class DashboardViewModel @ViewModelInject constructor(
  reducer: DashboardReducer,
  actionCreator: DashboardActionCreator
): BaseStateViewModel<DashboardState, DashboardEvent, BaseAction>(
  initialState = DashboardState(sportsModel = mapOf(), syncState = DashboardSyncState.Content),
  reducer = reducer,
  action = actionCreator
)
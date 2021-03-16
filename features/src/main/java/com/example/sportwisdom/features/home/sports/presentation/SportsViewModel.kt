package com.example.sportwisdom.features.home.sports.presentation

import androidx.hilt.lifecycle.ViewModelInject
import com.example.sportwisdom.common.utils.BaseAction
import com.example.sportwisdom.common.utils.BaseStateViewModel
import com.example.sportwisdom.domain.model.SportsModel
import com.example.sportwisdom.domain.reducer.home.HomeActionCreator
import com.example.sportwisdom.domain.reducer.home.HomeIntents
import com.example.sportwisdom.domain.reducer.sports.SportsReducer
import com.example.sportwisdom.domain.reducer.sports.state.SportState
import com.example.sportwisdom.domain.reducer.sports.state.SportSyncState
import kotlinx.coroutines.FlowPreview

@FlowPreview
class SportsViewModel @ViewModelInject constructor(
  reducer: SportsReducer,
  actionCreator: HomeActionCreator
): BaseStateViewModel<SportState, HomeIntents, BaseAction<*>>(
  initialState = SportState(sportsModel = SportsModel(emptyList()), syncState = SportSyncState.Content),
  reducer = reducer,
  action = actionCreator
)
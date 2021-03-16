package com.example.sportwisdom.features.home.sports.presentation

import androidx.hilt.lifecycle.ViewModelInject
import com.example.sportwisdom.common.utils.BaseAction
import com.example.sportwisdom.common.utils.BaseStateViewModel
import com.example.sportwisdom.features.home.sports.domain.model.SportsModel
import com.example.sportwisdom.features.home.domain.HomeActionCreator
import com.example.sportwisdom.features.home.domain.HomeIntents
import com.example.sportwisdom.features.home.sports.domain.reducer.SportsReducer
import com.example.sportwisdom.features.home.sports.domain.state.SportState
import com.example.sportwisdom.features.home.sports.domain.state.SportSyncState
import kotlinx.coroutines.FlowPreview

@FlowPreview
class SportsViewModel @ViewModelInject constructor(
  reducer: SportsReducer,
  actionCreator: HomeActionCreator
): com.example.sportwisdom.common.utils.BaseStateViewModel<SportState, HomeIntents, com.example.sportwisdom.common.utils.BaseAction<*>>(
  initialState = SportState(sportsModel = SportsModel(emptyList()), syncState = SportSyncState.Content),
  reducer = reducer,
  action = actionCreator
)
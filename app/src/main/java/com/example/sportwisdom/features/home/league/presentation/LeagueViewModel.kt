package com.example.sportwisdom.features.home.league.presentation

import androidx.hilt.lifecycle.ViewModelInject
import com.example.sportwisdom.base.BaseAction
import com.example.sportwisdom.base.BaseStateViewModel
import com.example.sportwisdom.features.home.domain.HomeActionCreator
import com.example.sportwisdom.features.home.domain.HomeEvent
import com.example.sportwisdom.features.home.league.domain.reducer.LeagueReducer
import com.example.sportwisdom.features.home.league.domain.state.LeagueState
import com.example.sportwisdom.features.home.league.domain.state.LeagueSyncState
import kotlinx.coroutines.FlowPreview

@FlowPreview
class LeagueViewModel @ViewModelInject constructor(
  reducer: LeagueReducer,
  actionCreator: HomeActionCreator
) : BaseStateViewModel<LeagueState, HomeEvent, BaseAction>(
  initialState = LeagueState(leagueModel = listOf(), syncState = LeagueSyncState.Content),
  reducer = reducer,
  action = actionCreator
)
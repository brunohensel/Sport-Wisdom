package com.example.sportwisdom.features.home.league.presentation

import androidx.hilt.lifecycle.ViewModelInject
import com.example.sportwisdom.common.utils.BaseAction
import com.example.sportwisdom.common.utils.BaseStateViewModel
import com.example.sportwisdom.domain.reducer.home.HomeActionCreator
import com.example.sportwisdom.domain.reducer.home.HomeIntents
import com.example.sportwisdom.domain.reducer.league.LeagueReducer
import com.example.sportwisdom.domain.reducer.league.state.LeagueState
import com.example.sportwisdom.domain.reducer.league.state.LeagueSyncState
import kotlinx.coroutines.FlowPreview

@FlowPreview
class LeagueViewModel @ViewModelInject constructor(
  reducer: LeagueReducer,
  actionCreator: HomeActionCreator
) : BaseStateViewModel<LeagueState, HomeIntents, BaseAction<*>>(
  initialState = LeagueState(leagueModel = listOf(), syncState = LeagueSyncState.Content),
  reducer = reducer,
  action = actionCreator
)
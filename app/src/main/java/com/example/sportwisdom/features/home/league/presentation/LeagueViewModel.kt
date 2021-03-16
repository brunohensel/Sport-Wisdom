package com.example.sportwisdom.features.home.league.presentation

import androidx.hilt.lifecycle.ViewModelInject
import com.example.sportwisdom.common.utils.BaseAction
import com.example.sportwisdom.common.utils.BaseStateViewModel
import com.example.sportwisdom.features.home.domain.HomeActionCreator
import com.example.sportwisdom.features.home.domain.HomeIntents
import com.example.sportwisdom.features.home.league.domain.reducer.LeagueReducer
import com.example.sportwisdom.features.home.league.domain.state.LeagueState
import com.example.sportwisdom.features.home.league.domain.state.LeagueSyncState
import kotlinx.coroutines.FlowPreview

@FlowPreview
class LeagueViewModel @ViewModelInject constructor(
  reducer: LeagueReducer,
  actionCreator: HomeActionCreator
) : com.example.sportwisdom.common.utils.BaseStateViewModel<LeagueState, HomeIntents, com.example.sportwisdom.common.utils.BaseAction<*>>(
  initialState = LeagueState(leagueModel = listOf(), syncState = LeagueSyncState.Content),
  reducer = reducer,
  action = actionCreator
)
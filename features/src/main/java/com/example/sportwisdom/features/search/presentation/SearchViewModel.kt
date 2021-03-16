package com.example.sportwisdom.features.search.presentation

import androidx.hilt.lifecycle.ViewModelInject
import com.example.sportwisdom.common.utils.BaseAction
import com.example.sportwisdom.common.utils.BaseStateViewModel
import com.example.sportwisdom.domain.reducer.search.SearchActionCreator
import com.example.sportwisdom.domain.reducer.search.SearchIntents
import com.example.sportwisdom.domain.reducer.search.SearchReducer
import com.example.sportwisdom.domain.reducer.search.state.SearchState
import com.example.sportwisdom.domain.reducer.search.state.SearchSyncState
import kotlinx.coroutines.FlowPreview

@FlowPreview
class SearchViewModel @ViewModelInject constructor(
  reducer: SearchReducer,
  actionCreator: SearchActionCreator
) : BaseStateViewModel<SearchState, SearchIntents, BaseAction<*>>(
  initialState = SearchState(
    teamsModel = emptyList(),
    SearchSyncState.Content
  ),
  reducer = reducer,
  action = actionCreator
)
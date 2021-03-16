package com.example.sportwisdom.features.search.presentation

import androidx.hilt.lifecycle.ViewModelInject
import com.example.sportwisdom.common.utils.BaseAction
import com.example.sportwisdom.common.utils.BaseStateViewModel
import com.example.sportwisdom.features.search.domain.reducer.SearchActionCreator
import com.example.sportwisdom.features.search.domain.reducer.SearchIntents
import com.example.sportwisdom.features.search.domain.reducer.SearchReducer
import com.example.sportwisdom.features.search.domain.state.SearchState
import com.example.sportwisdom.features.search.domain.state.SearchSyncState
import kotlinx.coroutines.FlowPreview

@FlowPreview
class SearchViewModel @ViewModelInject constructor(
  reducer: SearchReducer,
  actionCreator: SearchActionCreator
) : com.example.sportwisdom.common.utils.BaseStateViewModel<SearchState, SearchIntents, com.example.sportwisdom.common.utils.BaseAction<*>>(
  initialState = SearchState(teamsModel = emptyList(), SearchSyncState.Content),
  reducer = reducer,
  action = actionCreator
)
package com.example.sportwisdom.features.favorite.presentation

import androidx.hilt.lifecycle.ViewModelInject
import com.example.sportwisdom.common.utils.BaseAction
import com.example.sportwisdom.domain.reducer.favorite.FavoriteActionCreator
import com.example.sportwisdom.domain.reducer.favorite.FavoriteIntents
import com.example.sportwisdom.domain.reducer.favorite.FavoriteReducer
import com.example.sportwisdom.domain.reducer.favorite.state.FavoriteState
import com.example.sportwisdom.domain.reducer.favorite.state.FavoriteSyncState
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.flowOf

@FlowPreview
class FavoriteViewModel @ViewModelInject constructor(
  reducer: FavoriteReducer,
  actionCreator: FavoriteActionCreator
) : com.example.sportwisdom.common.utils.BaseStateViewModel<FavoriteState, FavoriteIntents, BaseAction<*>>(
  initialState = FavoriteState(
    favoriteModel = flowOf(emptyList()),
    syncState = FavoriteSyncState.Content
  ),
  reducer = reducer,
  action = actionCreator
)
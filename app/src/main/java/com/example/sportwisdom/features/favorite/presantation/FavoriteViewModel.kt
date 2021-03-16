package com.example.sportwisdom.features.favorite.presantation

import androidx.hilt.lifecycle.ViewModelInject
import com.example.sportwisdom.common.utils.BaseAction
import com.example.sportwisdom.common.utils.BaseStateViewModel
import com.example.sportwisdom.features.favorite.domain.reducer.FavoriteActionCreator
import com.example.sportwisdom.features.favorite.domain.reducer.FavoriteIntents
import com.example.sportwisdom.features.favorite.domain.reducer.FavoriteReducer
import com.example.sportwisdom.features.favorite.domain.state.FavoriteState
import com.example.sportwisdom.features.favorite.domain.state.FavoriteSyncState
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.flowOf

@FlowPreview
class FavoriteViewModel @ViewModelInject constructor(
  reducer: FavoriteReducer,
  actionCreator: FavoriteActionCreator
) : com.example.sportwisdom.common.utils.BaseStateViewModel<FavoriteState, FavoriteIntents, com.example.sportwisdom.common.utils.BaseAction<*>>(
  initialState = FavoriteState(favoriteModel = flowOf(emptyList()), syncState = FavoriteSyncState.Content),
  reducer = reducer,
  action = actionCreator
)
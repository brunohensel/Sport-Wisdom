package com.example.sportwisdom.features.home.domain

import com.example.redux.Action
import com.example.sportwisdom.base.BaseAction
import com.example.sportwisdom.features.home.data.HomeRepository
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flattenMerge
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

@FlowPreview
class HomeActionCreator @Inject constructor(private val repository: HomeRepository) : Action<HomeEvent, BaseAction> {

  override fun invoke(event: HomeEvent): Flow<BaseAction> {
    return flow {
      val result = when (event) {
        is HomeEvent.FetchLeagues -> repository.fetchAllLeagues(event.sportType)
        HomeEvent.FetchSports  -> repository.fetchAllSports()
      }
      emit(result)
    }
      .flattenMerge()
      .onStart { emit(BaseAction.Executing) }
  }
}

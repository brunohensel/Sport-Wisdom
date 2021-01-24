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
class HomeActionCreator @Inject constructor(private val repository: HomeRepository) : Action<HomeIntents, BaseAction> {

  override fun invoke(intents: HomeIntents): Flow<BaseAction> {
    return flow {
      val result = when (intents) {
        is HomeIntents.FetchLeagues -> repository.fetchAllLeagues(intents.sportType)
        is HomeIntents.FetchEvents  -> repository.fetchEvents(intents.leagueId)
        is HomeIntents.InsertEvent  -> repository.insertEvent(intents.eventDto)
        HomeIntents.FetchSports     -> repository.fetchAllSports()
      }
      emit(result)
    }
      .flattenMerge()
      .onStart { emit(BaseAction.Executing) }
  }
}

package com.example.sportwisdom.features.schedule.domain.reducer

import com.example.redux.Action
import com.example.sportwisdom.base.BaseAction
import com.example.sportwisdom.features.schedule.data.ScheduleRepository
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flattenMerge
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

@FlowPreview
class ScheduleActionCreator @Inject constructor(private val repository: ScheduleRepository) : Action<ScheduleIntents, BaseAction> {
  override fun invoke(intents: ScheduleIntents): Flow<BaseAction> {
    return flow {
      val result = when (intents) {
        ScheduleIntents.FetchCachedEvents -> repository.fetchCachedEvents()
        ScheduleIntents.DeleteAllEvents -> repository.deleteAllEvents()
        is ScheduleIntents.DeleteEvent -> repository.deleteEvent(intents.eventId)
      }
      emit(result)
    }
      .flattenMerge()
      .onStart { emit(BaseAction.Executing) }
  }
}
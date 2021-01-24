package com.example.sportwisdom.features.schedule.domain.reducer

import com.example.redux.Action
import com.example.sportwisdom.base.BaseAction
import com.example.sportwisdom.features.schedule.data.ScheduleRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ScheduleActionCreator @Inject constructor(private val repository: ScheduleRepository) : Action<ScheduleIntents, BaseAction>{
  override fun invoke(intents: ScheduleIntents): Flow<BaseAction> {
    when(intents){
      ScheduleIntents.FetchCachedEvents -> TODO()
      ScheduleIntents.DeleteAllEvents   -> TODO()
      is ScheduleIntents.DeleteEvent    -> TODO()
    }
  }
}
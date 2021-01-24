package com.example.sportwisdom.features.schedule.domain.reducer

import com.example.redux.Reducer
import com.example.sportwisdom.base.BaseAction
import com.example.sportwisdom.features.home.events.domain.model.EventDto
import com.example.sportwisdom.features.schedule.domain.state.ScheduleState
import com.example.sportwisdom.features.schedule.domain.state.ScheduleSyncState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class ScheduleReducer @Inject constructor() : Reducer<ScheduleState, BaseAction<*>> {
  override fun invoke(currentState: ScheduleState, action: BaseAction<*>): ScheduleState {
    return when (action) {
      BaseAction.Executing           -> currentState.copy(syncState = ScheduleSyncState.Loading)
      BaseAction.EmptyResult         -> currentState.copy(syncState = ScheduleSyncState.Empty)
      is BaseAction.CacheSuccess<*>  -> currentState.copy(scheduleModel = action.value as Flow<List<EventDto>>, syncState = ScheduleSyncState.Content)
      is BaseAction.Failed           -> currentState.copy(syncState = ScheduleSyncState.Message(action.reason))
      is BaseAction.SideEffect       -> currentState.copy(syncState = ScheduleSyncState.SideEffect)
      else                           -> throw IllegalStateException("Wrong Action: $action for this Reducer: ${javaClass.simpleName}")
    }
  }
}
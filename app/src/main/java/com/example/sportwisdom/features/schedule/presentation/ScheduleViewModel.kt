package com.example.sportwisdom.features.schedule.presentation

import androidx.hilt.lifecycle.ViewModelInject
import com.example.sportwisdom.base.BaseAction
import com.example.sportwisdom.base.BaseStateViewModel
import com.example.sportwisdom.features.schedule.domain.reducer.ScheduleActionCreator
import com.example.sportwisdom.features.schedule.domain.reducer.ScheduleIntents
import com.example.sportwisdom.features.schedule.domain.reducer.ScheduleReducer
import com.example.sportwisdom.features.schedule.domain.state.ScheduleState
import com.example.sportwisdom.features.schedule.domain.state.ScheduleSyncState
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

@FlowPreview
class ScheduleViewModel @ViewModelInject constructor(
  reducer: ScheduleReducer,
  action: ScheduleActionCreator
) : BaseStateViewModel<ScheduleState, ScheduleIntents, BaseAction>(
  initialState = ScheduleState(scheduleModel = flowOf(emptyList()), syncState = ScheduleSyncState.Content),
  reducer = reducer,
  action = action
)
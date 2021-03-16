package com.example.sportwisdom.features.schedule.presentation

import androidx.hilt.lifecycle.ViewModelInject
import com.example.sportwisdom.common.utils.BaseAction
import com.example.sportwisdom.common.utils.BaseStateViewModel
import com.example.sportwisdom.features.schedule.domain.reducer.ScheduleActionCreator
import com.example.sportwisdom.features.schedule.domain.reducer.ScheduleIntents
import com.example.sportwisdom.features.schedule.domain.reducer.ScheduleReducer
import com.example.sportwisdom.features.schedule.domain.state.ScheduleState
import com.example.sportwisdom.features.schedule.domain.state.ScheduleSyncState
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.flowOf

@FlowPreview
class ScheduleViewModel @ViewModelInject constructor(
  reducer: ScheduleReducer,
  action: ScheduleActionCreator
) : com.example.sportwisdom.common.utils.BaseStateViewModel<ScheduleState, ScheduleIntents, com.example.sportwisdom.common.utils.BaseAction<*>>(
  initialState = ScheduleState(scheduleModel = flowOf(emptyList()), syncState = ScheduleSyncState.Content),
  reducer = reducer,
  action = action
)
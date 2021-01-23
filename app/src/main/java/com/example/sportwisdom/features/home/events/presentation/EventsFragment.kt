package com.example.sportwisdom.features.home.events.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.example.sportwisdom.R
import com.example.sportwisdom.features.home.domain.HomeEvent
import com.example.sportwisdom.features.home.events.domain.state.EventSyncState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map

@FlowPreview
@AndroidEntryPoint
class EventsFragment : Fragment(R.layout.fragment_events) {

  private val viewModel: EventsViewModel by viewModels()
  private val args: EventsFragmentArgs by navArgs()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    lifecycleScope.launchWhenCreated { viewModel.process(flowOf(HomeEvent.FetchEvents(args.leagueId)))}

    lifecycleScope.launchWhenStarted {
      viewModel
        .state
        .map {eventState ->
          when(eventState.syncState){
            EventSyncState.Loading    -> TODO()
            EventSyncState.Content    -> TODO()
            is EventSyncState.Message -> TODO()
          }
        }.launchIn(lifecycleScope)
    }
  }
}
package com.example.sportwisdom.features.home.league.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.example.sportwisdom.R
import com.example.sportwisdom.features.home.domain.HomeEvent
import com.example.sportwisdom.features.home.league.domain.state.LeagueSyncState
import com.example.sportwisdom.features.home.sports.presentation.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import timber.log.Timber

@FlowPreview
@AndroidEntryPoint
class LeagueFragment : Fragment(R.layout.fragment_league) {

  private val args: LeagueFragmentArgs by navArgs()
  private val viewModel: LeagueViewModel by viewModels()


  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    lifecycleScope.launchWhenCreated { viewModel.process(listOf(HomeEvent.FetchLeagues(args.sportDto.strSport)).asFlow())}

    lifecycleScope.launchWhenStarted {
      viewModel
        .state
        .map {leagueState ->
          when(leagueState.syncState){
            LeagueSyncState.Loading    -> Timber.i("Loading")
            LeagueSyncState.Content    -> Timber.i("List of: ${leagueState.leagueModel}")
            is LeagueSyncState.Message -> Timber.i("$leagueState")
          }
        }.launchIn(lifecycleScope)
    }
  }
}
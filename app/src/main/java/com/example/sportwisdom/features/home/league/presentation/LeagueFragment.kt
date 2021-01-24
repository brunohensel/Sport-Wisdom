package com.example.sportwisdom.features.home.league.presentation

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.sportwisdom.R
import com.example.sportwisdom.features.home.domain.HomeIntents
import com.example.sportwisdom.features.home.league.domain.model.LeagueDto
import com.example.sportwisdom.features.home.league.domain.state.LeagueSyncState
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_league.*
import kotlinx.android.synthetic.main.fragment_league.txtEmptyLeagueState
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map

@FlowPreview
@AndroidEntryPoint
class LeagueFragment : Fragment(R.layout.fragment_league) {

  private val args: LeagueFragmentArgs by navArgs()
  private val viewModel: LeagueViewModel by viewModels()
  private val leaguesAdapter by lazy { LeaguesAdapter(::onItemClick) }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    lifecycleScope.launchWhenCreated { viewModel.process(listOf(HomeIntents.FetchLeagues(args.sportDto.strSport)).asFlow()) }

    lifecycleScope.launchWhenStarted {
      viewModel
        .state
        .map { leagueState ->
          when (leagueState.syncState) {
            LeagueSyncState.Loading -> progressBarLeagues.isVisible = true
            LeagueSyncState.Content -> displayLeagues(leagueState.leagueModel)
            LeagueSyncState.Empty   -> handleEmptyState()
            is LeagueSyncState.Message -> displayErrorMessage(leagueState.syncState.msg)
          }
        }.launchIn(lifecycleScope)
    }
  }

  private fun handleEmptyState() {
    txtEmptyLeagueState.isVisible = true
    progressBarLeagues.isVisible = false
  }

  private fun displayLeagues(leagueModel: List<LeagueDto>) {
    progressBarLeagues.isVisible = false
    leaguesAdapter.submitList(leagueModel)
  }

  private fun onItemClick(leagueDto: LeagueDto) {
    val action = LeagueFragmentDirections.actionLeagueFragmentToEventsFragment(leagueDto.id.toInt())
    findNavController().navigate(action)
  }

  private fun displayErrorMessage(msg: String?) {
    progressBarLeagues.isVisible = false
    Snackbar.make(requireView(), msg ?: "Unknown error", Snackbar.LENGTH_LONG).show()
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    rvLeagues.adapter = leaguesAdapter
  }
}
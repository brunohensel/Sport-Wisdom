package com.example.sportwisdom.features.home.sports.presentation

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.sportwisdom.R
import com.example.sportwisdom.features.home.domain.HomeEvent
import com.example.sportwisdom.features.home.sports.domain.model.SportDto
import com.example.sportwisdom.features.home.sports.domain.state.SportSyncState
import com.google.android.flexbox.AlignItems
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_league.*
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map

@FlowPreview
@AndroidEntryPoint
class SportsFragment : Fragment(R.layout.fragment_home) {

  private val viewModel: SportsViewModel by viewModels()
  private val homeAdapter by lazy { SportsAdapter(::onItemClicked) }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    lifecycleScope.launchWhenCreated { viewModel.process(listOf(HomeEvent.FetchSports).asFlow()) }

    lifecycleScope.launchWhenStarted {
      viewModel
        .state
        .map { homeState ->
          when (homeState.syncState) {
            SportSyncState.Loading -> progressBar.isVisible = true
            SportSyncState.Content -> displayAllSports(homeState.sportsModel.sports)
            is SportSyncState.Message -> displayErrorMessage(homeState.syncState.msg)
          }
        }.launchIn(lifecycleScope)
    }
  }

  private fun displayAllSports(sports: List<SportDto>) {
    progressBar.isVisible = false
    homeAdapter.submitList(sports)
  }

  private fun displayErrorMessage(msg: String?) {
    progressBarLeagues.isVisible = false
    Snackbar.make(requireView(), msg ?: "Unknown error", Snackbar.LENGTH_LONG).show()
  }

  private fun onItemClicked(sportType: SportDto) {
    val action = SportsFragmentDirections.actionHomeFragmentToLeagueFragment(sportType)
    findNavController().navigate(action)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    rvHomeSports.apply {
      adapter = homeAdapter
      layoutManager = FlexboxLayoutManager(context).apply {
        flexWrap = FlexWrap.WRAP
        justifyContent = JustifyContent.SPACE_AROUND
        alignItems = AlignItems.CENTER
      }
    }
  }
}
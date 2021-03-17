package com.example.sportwisdom.features.home.sports.presentation

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.sportwisdom.domain.model.SportDto
import com.example.sportwisdom.domain.reducer.home.HomeIntents.FetchSports
import com.example.sportwisdom.domain.reducer.sports.state.SportSyncState
import com.example.sportwisdom.features.R
import com.google.android.flexbox.AlignItems
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_home.*
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

    lifecycleScope.launchWhenCreated { viewModel.process(listOf(FetchSports).asFlow()) }

    lifecycleScope.launchWhenStarted {
      viewModel
        .state
        .map { homeState ->
          when (homeState.syncState) {
            SportSyncState.Loading -> progressBar.isVisible = true
            SportSyncState.Content -> displayAllSports(homeState.sportsModel.sports)
            SportSyncState.Empty   -> handleEmptyState()
            is SportSyncState.Message -> displayErrorMessage((homeState.syncState as SportSyncState.Message).msg)
          }
        }.launchIn(lifecycleScope)
    }
  }

  private fun handleEmptyState() {
    txtEmptySportState.isVisible = true
    progressBar.isVisible = false
  }

  private fun displayAllSports(sports: List<SportDto>) {
    progressBar.isVisible = false
    homeAdapter.submitList(sports)
  }

  private fun displayErrorMessage(msg: String?) {
    progressBar.isVisible = false
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
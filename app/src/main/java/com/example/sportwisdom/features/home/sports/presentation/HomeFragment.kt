package com.example.sportwisdom.features.home.sports.presentation

import android.content.Context
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
import com.example.sportwisdom.features.home.sports.domain.state.HomeSyncState
import com.google.android.flexbox.AlignItems
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import timber.log.Timber

@FlowPreview
@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

  private val viewModel: HomeViewModel by viewModels()
  private val homeAdapter by lazy { HomeAdapter(::onItemClicked) }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    lifecycleScope.launchWhenCreated { viewModel.process(listOf(HomeEvent.FetchSports).asFlow()) }

    lifecycleScope.launchWhenStarted {
      viewModel
        .state
        .map { homeState ->
          when (homeState.syncState) {
            HomeSyncState.Loading -> progressBar.isVisible = true
            HomeSyncState.Content -> displayAllSports(homeState.sportsModel.sports)
            is HomeSyncState.Message -> Timber.i("Current state: $homeState")
          }
        }.launchIn(lifecycleScope)
    }
  }

  private fun displayAllSports(sports: List<SportDto>) {
    progressBar.isVisible = false
    homeAdapter.submitList(sports)
  }

  private fun onItemClicked(sportType: SportDto) {
    val action = HomeFragmentDirections.actionHomeFragmentToLeagueFragment(sportType)
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
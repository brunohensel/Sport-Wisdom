package com.example.sportwisdom.features.favorite.presentation

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.sportwisdom.domain.reducer.favorite.FavoriteIntents.DeleteTeam
import com.example.sportwisdom.domain.reducer.favorite.FavoriteIntents.FetchCachedTeams
import com.example.sportwisdom.domain.reducer.favorite.state.FavoriteSyncState
import com.example.sportwisdom.features.R
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_favorite.*
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

@FlowPreview
@AndroidEntryPoint
class FavoriteFragment : Fragment(R.layout.fragment_favorite) {

  private val viewModel: FavoriteViewModel by viewModels()
  private val favoriteAdapter by lazy { FavoriteAdapter(::onClick, ::onDelete) }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    lifecycleScope.launchWhenCreated { viewModel.process(flowOf(FetchCachedTeams)) }

    lifecycleScope.launchWhenStarted {
      viewModel
        .state
        .map { favoriteState ->
          when (favoriteState.syncState) {
            FavoriteSyncState.SideEffect -> setProgressBar(false)
            FavoriteSyncState.Loading    -> setProgressBar(true)
            FavoriteSyncState.Content    -> displayTeams(favoriteState.favoriteModel)
            FavoriteSyncState.Empty      -> handleEmptyState()
            is FavoriteSyncState.Message -> displayErrorMessage((favoriteState.syncState as FavoriteSyncState.Message).msg)
          }
        }.launchIn(lifecycleScope)
    }
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    rvCachedTeams.adapter = favoriteAdapter
  }

  private fun displayTeams(favoriteModel: Flow<List<com.example.sportwisdom.domain.model.TeamDto>>) {
    setProgressBar(false)
    lifecycleScope.launch {
      favoriteModel.collect { teams ->
        txtEmptyCachedTeamState.isVisible = teams.isEmpty()
        favoriteAdapter.submitList(teams)
      }
    }
  }

  private fun onClick(teamDto: com.example.sportwisdom.domain.model.TeamDto) {
    val action = FavoriteFragmentDirections.actionFavoriteFragmentToTeamDetailFragment(teamDto)
    findNavController().navigate(action)
  }

  private fun onDelete(teamDto: com.example.sportwisdom.domain.model.TeamDto) {
    lifecycleScope.launch {
      viewModel.process(flowOf(DeleteTeam(teamDto.idTeam)))
    }
  }

  private fun setProgressBar(isVisible: Boolean) {
    progressBarCachedTeams.isVisible = isVisible
  }

  private fun handleEmptyState() {
    txtEmptyCachedTeamState.isVisible = true
    setProgressBar(false)
  }

  private fun displayErrorMessage(msg: String?) {
    setProgressBar(false)
    Snackbar.make(requireView(), msg ?: "Unknown error", Snackbar.LENGTH_LONG).show()
  }
}
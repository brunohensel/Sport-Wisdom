package com.example.sportwisdom.features.search.presentation

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.sportwisdom.R
import com.example.sportwisdom.features.search.domain.model.TeamDto
import com.example.sportwisdom.features.search.domain.reducer.SearchIntents
import com.example.sportwisdom.features.search.domain.state.SearchSyncState
import com.example.sportwisdom.util.getTextAfterChangeAsFlow
import com.example.sportwisdom.util.hideSoftKeyboard
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_search.*
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

@FlowPreview
@AndroidEntryPoint
class SearchFragment : Fragment(R.layout.fragment_search) {

  private val viewModel: SearchViewModel by viewModels()
  private val searchAdapter by lazy { SearchAdapter(::addToFavorite, ::openDetail) }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    lifecycleScope.launchWhenStarted {
      viewModel
        .state
        .map { searchState ->
          when (searchState.syncState) {
            SearchSyncState.Loading -> progressBarSearch.isVisible = true
            SearchSyncState.Empty -> handleEmptyState()
            SearchSyncState.Content -> displayTeams(searchState.teamsModel)
            SearchSyncState.SideEffect -> progressBarSearch.isVisible = false
            is SearchSyncState.Message -> displayErrorMessage(searchState.syncState.msg)
          }
        }.launchIn(lifecycleScope)
    }
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    rvSearchTeams.adapter = searchAdapter

    lifecycleScope.launch {
      edtSearchTeam
        .getTextAfterChangeAsFlow()
        .debounce(800)
        .map { query ->
          viewModel.process(flowOf(SearchIntents.SearchForTeamsByName(query)))
          imgClearText.isVisible = query.isNotEmpty()
          if (query.isEmpty()) {
            requireActivity().hideSoftKeyboard()
          }
        }
        .collect()
    }

    imgClearText.setOnClickListener { edtSearchTeam.text.clear() }
  }

  private fun displayTeams(teamsModel: List<TeamDto>) {
    rvSearchTeams.isVisible = true
    requireActivity().hideSoftKeyboard()
    progressBarSearch.isVisible = false
    searchAdapter.submitList(teamsModel)
  }

  private fun addToFavorite(teamDto: TeamDto) {

  }

  private fun openDetail(teamDto: TeamDto) {
    val action = SearchFragmentDirections.actionSearchFragmentToTeamDetailFragment(teamDto)
    findNavController().navigate(action)
  }

  private fun handleEmptyState() {
    requireActivity().hideSoftKeyboard()
    rvSearchTeams.isVisible = false
    txtEmptySearchState.isVisible = true
    progressBarSearch.isVisible = false
  }

  private fun displayErrorMessage(msg: String?) {
    requireActivity().hideSoftKeyboard()
    progressBarSearch.isVisible = false
    Snackbar.make(requireView(), msg ?: "Unknown error", Snackbar.LENGTH_LONG).show()
  }
}
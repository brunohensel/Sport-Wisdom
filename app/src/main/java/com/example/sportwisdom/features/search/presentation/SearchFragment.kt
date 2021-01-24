package com.example.sportwisdom.features.search.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.sportwisdom.R
import com.example.sportwisdom.features.search.domain.reducer.SearchIntents
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import timber.log.Timber

@FlowPreview
@AndroidEntryPoint
class SearchFragment : Fragment(R.layout.fragment_search) {

  private val viewModel: SearchViewModel by viewModels()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    lifecycleScope.launchWhenCreated { viewModel.process(flowOf(SearchIntents.SearchForTeamsByName("Arsenal"))) }

    lifecycleScope.launchWhenStarted {
      viewModel
        .state
        .map {
          Timber.i("Search state: $it")
        }.launchIn(lifecycleScope)
    }
  }
}
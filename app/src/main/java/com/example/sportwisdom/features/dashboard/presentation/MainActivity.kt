package com.example.sportwisdom.features.dashboard.presentation

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.sportwisdom.R
import com.example.sportwisdom.features.dashboard.domain.reducer.DashboardEvent
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import timber.log.Timber

@FlowPreview
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

  private val viewModel: DashboardViewModel by viewModels()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    lifecycleScope.launchWhenCreated { viewModel.process(listOf(DashboardEvent.fetchSports).asFlow()) }

    lifecycleScope.launchWhenStarted {
      viewModel
        .state
        .collect {
          Timber.i("Current state: $it")
        }
    }
  }
}
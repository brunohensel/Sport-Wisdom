package com.example.sportwisdom.dashboard

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.example.sportwisdom.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.FlowPreview

@FlowPreview
@AndroidEntryPoint
class DashboardActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    setupAppBarConfig()
  }

  override fun onSupportNavigateUp() = Navigation.findNavController(this, R.id.nav_host_fragment).navigateUp()

  private fun setupAppBarConfig() {
    val navHostFragment: NavHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
    val navController: NavController = navHostFragment.navController
    val appBarConfiguration = AppBarConfiguration(navController.graph)
    topAppBar.setupWithNavController(navController, appBarConfiguration)
    NavigationUI.setupWithNavController(bottom_navigation, navController)
  }
}
package com.example.sportwisdom.features.search.presentation

import android.os.Bundle
import android.view.View
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import coil.load
import com.example.sportwisdom.R
import com.google.android.material.appbar.AppBarLayout
import kotlinx.android.synthetic.main.fragment_team_detail.*

class TeamDetailFragment : Fragment(R.layout.fragment_team_detail) {

  private val args: TeamDetailFragmentArgs by navArgs()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    imgStadium.load(args.team.strStadiumThumb){
      crossfade(true)
    }

    imgTeamBadge.load(args.team.strTeamBadge)
    coordinateMotion()
  }

  private fun coordinateMotion() {
    val appBarLayout: AppBarLayout = appbar_layout
    val motionLayout: MotionLayout = motion_layout

    val listener = AppBarLayout.OnOffsetChangedListener { _, verticalOffset ->
      val seekPosition = -verticalOffset / appBarLayout.totalScrollRange.toFloat()
      motionLayout.progress = seekPosition
    }

    appBarLayout.addOnOffsetChangedListener(listener)
  }
}
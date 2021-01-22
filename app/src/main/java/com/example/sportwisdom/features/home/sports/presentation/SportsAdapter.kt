package com.example.sportwisdom.features.home.sports.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.example.sportwisdom.R
import com.example.sportwisdom.features.home.sports.domain.model.SportDto
import kotlinx.android.synthetic.main.item_home_sports.view.*

class SportsAdapter(private val onItemClick: (SportDto) -> Unit) : ListAdapter<SportDto, SportsAdapter.HomeViewHolder>(SportsAdapter) {

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
    return HomeViewHolder.from(parent)
  }

  override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
    holder.bind(getItem(position), onItemClick)
  }

  class HomeViewHolder private constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(data: SportDto, onItemClick: (SportDto) -> Unit) {
      with(itemView) {
        ctlSports.setOnClickListener { onItemClick(data) }
        txtSportType.text = data.strSport
        imgHomeSport.load(data.strSportThumb) {
          crossfade(true)
          crossfade(700)
          transformations(CircleCropTransformation())
        }
      }
    }

    companion object {
      fun from(parent: ViewGroup): HomeViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_home_sports, parent, false)
        return HomeViewHolder(view)
      }
    }
  }

  private companion object : DiffUtil.ItemCallback<SportDto>() {
    override fun areItemsTheSame(oldItem: SportDto, newItem: SportDto): Boolean {
      return oldItem.idSport == newItem.idSport
    }

    override fun areContentsTheSame(oldItem: SportDto, newItem: SportDto): Boolean {
      return oldItem == newItem
    }
  }
}
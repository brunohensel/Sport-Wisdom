package com.example.sportwisdom.features.home.league.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.sportwisdom.domain.model.LeagueDto
import com.example.sportwisdom.features.R
import com.example.sportwisdom.features.home.league.presentation.LeaguesAdapter.LeaguesViewHolder
import kotlinx.android.synthetic.main.item_leagues.view.*

class LeaguesAdapter(private val onItemClick: (LeagueDto) -> Unit) : ListAdapter<LeagueDto, LeaguesViewHolder>(LeaguesAdapter) {

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LeaguesViewHolder {
    return LeaguesViewHolder.from(parent)
  }

  override fun onBindViewHolder(holder: LeaguesViewHolder, position: Int) {
    holder.bind(getItem(position), onItemClick)
  }

  class LeaguesViewHolder private constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(data: LeagueDto, onItemClick: (LeagueDto) -> Unit) {
      with(itemView) {
        ctlLeague.setOnClickListener { onItemClick(data) }
        txtLeagueType.text = data.league
      }
    }

    companion object {
      fun from(parent: ViewGroup): LeaguesViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_leagues, parent, false)
        return LeaguesViewHolder(view)
      }
    }
  }

  private companion object : DiffUtil.ItemCallback<LeagueDto>() {
    override fun areItemsTheSame(oldItem: LeagueDto, newItem: LeagueDto): Boolean {
      return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: LeagueDto, newItem: LeagueDto): Boolean {
      return oldItem == newItem
    }
  }
}
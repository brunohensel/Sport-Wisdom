package com.example.sportwisdom.features.favorite.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.sportwisdom.domain.model.TeamDto
import com.example.sportwisdom.features.R
import com.example.sportwisdom.features.favorite.presentation.FavoriteAdapter.FavoriteViewHolder
import kotlinx.android.synthetic.main.item_team.view.*

class FavoriteAdapter(private val onclick: (TeamDto) -> Unit, private val onDelete: (TeamDto) -> Unit) : ListAdapter<TeamDto, FavoriteViewHolder>(
  FavoriteAdapter
) {

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
    return FavoriteViewHolder.from(parent)
  }

  override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
    holder.bind(getItem(position), onclick, onDelete)
  }

  class FavoriteViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    fun bind(data: TeamDto, onclick: (TeamDto) -> Unit, onDelete: (TeamDto) -> Unit) {
      with(itemView) {
        imgDeleteFavorite.setOnClickListener { onDelete(data) }
        cardViewFavorite.setOnClickListener { onclick(data) }
        txtFavoriteTeamName.text = data.strTeam
        imgFavoriteTeam.load(data.strTeamBadge) {
          crossfade(true)
          crossfade(300)
        }
      }
    }

    companion object {
      fun from(parent: ViewGroup): FavoriteViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_team, parent, false)
        return FavoriteViewHolder(view)
      }
    }
  }

  private companion object : DiffUtil.ItemCallback<TeamDto>() {
    override fun areItemsTheSame(oldItem: TeamDto, newItem: TeamDto): Boolean {
      return oldItem.idTeam == newItem.idTeam
    }

    override fun areContentsTheSame(oldItem: TeamDto, newItem: TeamDto): Boolean {
      return oldItem == newItem
    }
  }
}
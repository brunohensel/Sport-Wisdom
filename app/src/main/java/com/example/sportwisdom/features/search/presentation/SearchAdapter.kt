package com.example.sportwisdom.features.search.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.example.sportwisdom.R
import com.example.sportwisdom.features.search.domain.model.TeamDto
import com.example.sportwisdom.features.search.presentation.SearchAdapter.SearchViewHolder
import kotlinx.android.synthetic.main.item_search.view.*

class SearchAdapter(
  private val addToFavorite: (TeamDto) -> Unit,
  private val openDetail: (TeamDto) -> Unit
) : ListAdapter<TeamDto, SearchViewHolder>(SearchAdapter) {

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
    return SearchViewHolder.from(parent)
  }

  override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
    holder.bind(getItem(position), addToFavorite, openDetail)
  }

  class SearchViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    fun bind(data: TeamDto, addToFavorite: (TeamDto) -> Unit, openDetail: (TeamDto) -> Unit) {
      with(itemView) {

        imgAddToFavorite.setOnClickListener { addToFavorite(data) }
        cardViewSearch.setOnClickListener { openDetail(data) }

        txtTeamName.text = data.strTeam

        data.strTeamBadge?.let {
          imgSearchTeamLogo.load(it) {
            crossfade(true)
            crossfade(200)
            transformations(CircleCropTransformation())
          }
        } ?: imgSearchTeamLogo.load(R.drawable.dafault_image)
      }
    }

    companion object {
      fun from(parent: ViewGroup): SearchViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_search, parent, false)
        return SearchViewHolder(view)
      }
    }
  }

  private companion object : DiffUtil.ItemCallback<TeamDto>() {
    override fun areItemsTheSame(oldItem: TeamDto, newItem: TeamDto): Boolean {
      return oldItem.idTeam == oldItem.idTeam
    }

    override fun areContentsTheSame(oldItem: TeamDto, newItem: TeamDto): Boolean {
      return oldItem == newItem
    }
  }

}
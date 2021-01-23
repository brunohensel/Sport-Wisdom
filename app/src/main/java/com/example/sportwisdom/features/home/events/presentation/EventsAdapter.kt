package com.example.sportwisdom.features.home.events.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.sportwisdom.R
import com.example.sportwisdom.features.home.events.domain.model.EventDto
import com.example.sportwisdom.features.home.events.presentation.EventsAdapter.EventsViewHolder
import kotlinx.android.synthetic.main.item_events.view.*

class EventsAdapter : ListAdapter<EventDto, EventsViewHolder>(EventsAdapter) {

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventsViewHolder {
    return EventsViewHolder.from(parent)
  }

  override fun onBindViewHolder(holder: EventsViewHolder, position: Int) {
    holder.bind(getItem(position))
  }

  class EventsViewHolder private constructor(view: View) : RecyclerView.ViewHolder(view) {

    fun bind(data: EventDto) {
      with(itemView) {
        txtEvent.text = data.strEvent
        txtVenue.text = if (data.strVenue.isNullOrBlank()) "Unavailable venue" else data.strVenue
        txtTime.text = if (data.strTime == "00:00:00") "Time not Known" else data.strTime
        txtTimeStamp.text = data.dateEvent

        imgAddToScheduler.setOnClickListener { }

        data.strThumb?.let { path ->
          imgThumbEvent.load(path) {
            crossfade(true)
            crossfade(400)
            error(R.drawable.dafault_image)
          }
        } ?: imgThumbEvent.load(R.drawable.dafault_image) {
          crossfade(true)
          crossfade(400)
        }
      }
    }

    companion object {
      fun from(parent: ViewGroup): EventsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_events, parent, false)
        return EventsViewHolder(view)
      }
    }
  }

  private companion object : DiffUtil.ItemCallback<EventDto>() {
    override fun areItemsTheSame(oldItem: EventDto, newItem: EventDto): Boolean {
      return oldItem.idEvent == newItem.idEvent
    }

    override fun areContentsTheSame(oldItem: EventDto, newItem: EventDto): Boolean {
      return oldItem == newItem
    }
  }
}
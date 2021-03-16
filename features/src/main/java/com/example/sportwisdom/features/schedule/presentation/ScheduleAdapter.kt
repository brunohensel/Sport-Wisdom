package com.example.sportwisdom.features.schedule.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.sportwisdom.common.utils.formatTo
import com.example.sportwisdom.common.utils.toDate
import com.example.sportwisdom.domain.model.EventDto
import com.example.sportwisdom.features.R
import com.example.sportwisdom.features.schedule.presentation.ScheduleAdapter.ScheduleViewHolder
import kotlinx.android.synthetic.main.item_cached_events.view.*

class ScheduleAdapter(private val onDeleteItem: (EventDto) -> Unit) : ListAdapter<EventDto, ScheduleViewHolder>(ScheduleAdapter) {

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduleViewHolder {
    return ScheduleViewHolder.from(parent)
  }

  override fun onBindViewHolder(holder: ScheduleViewHolder, position: Int) {
    holder.bind(getItem(position), onDeleteItem)
  }

  class ScheduleViewHolder private constructor(view: View) : RecyclerView.ViewHolder(view) {

    fun bind(data: EventDto, onDeleteItem: (EventDto) -> Unit) {
      with(itemView) {
        txtCachedEvent.text = data.strEvent
        txtCachedVenue.text = if (data.strVenue.isNullOrBlank()) "Unavailable venue" else data.strVenue
        txtCachedTime.text = data.strTimestamp?.toDate()?.formatTo("HH:mm") ?: if (data.strTime == "00:00:00") "Time not Known" else data.strTime
        txtCachedDate.text = data.dateEvent

        imgDeleteFromScheduler.setOnClickListener { onDeleteItem(data) }

        data.strThumb?.let { path ->
          imgThumbCachedEvent.load(path) {
            crossfade(true)
            crossfade(400)
            error(R.drawable.dafault_image)
          }
        } ?: imgThumbCachedEvent.load(R.drawable.dafault_image) {
          crossfade(true)
          crossfade(400)
        }
      }
    }

    companion object {
      fun from(parent: ViewGroup): ScheduleViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_cached_events, parent, false)
        return ScheduleViewHolder(view)
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
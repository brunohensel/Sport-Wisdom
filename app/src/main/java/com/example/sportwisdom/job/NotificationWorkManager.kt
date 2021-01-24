package com.example.sportwisdom.job

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.sportwisdom.features.home.events.presentation.EventsFragment.Companion.SCHEDULE_EXTRA_EVENT
import com.example.sportwisdom.features.home.events.presentation.EventsFragment.Companion.SCHEDULE_EXTRA_EVENT_ID
import com.example.sportwisdom.util.Notification
import kotlinx.coroutines.FlowPreview

@FlowPreview
class NotificationWorkManager(private val context: Context, params: WorkerParameters) : Worker(context, params) {

  override fun doWork(): Result {
    val eventName = inputData.getString(SCHEDULE_EXTRA_EVENT)
    val eventId = inputData.getInt(SCHEDULE_EXTRA_EVENT_ID, 0)

    Notification(context, eventName, eventId)

    return Result.success()
  }
}
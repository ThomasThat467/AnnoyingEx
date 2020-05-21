package com.example.annoyingex

import android.content.Context
import androidx.work.*

class MessageWorker (context: Context, workParams: WorkerParameters): Worker(context, workParams) {
    private val notificationManager = (context as AnnoyingExApp).messageNotificationManager

    // Send the message
    override fun doWork(): Result {
        notificationManager.makeMessage()

        return Result.success()
    }
}
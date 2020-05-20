package com.example.annoyingex

import android.content.Context
import android.util.Log
import androidx.work.*
import com.example.annoyingex.manager.MessageNotificationManager

class MessageWorker (private val context: Context, workParams: WorkerParameters): Worker(context, workParams) {
    private val notificationManager = (context as AnnoyingExApp).messageNotificationManager

    override fun doWork(): Result {
        notificationManager.makeMessage()

        return Result.retry()
    }
}
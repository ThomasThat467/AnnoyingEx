package com.example.annoyingex.manager

import android.content.Context
import androidx.work.*
import com.example.annoyingex.JsonWorker
import com.example.annoyingex.MessageWorker
import java.util.concurrent.TimeUnit

class MessageWorkManager(context: Context) {
    private var workManager = WorkManager.getInstance(context)

    // Begin sending scheduled messages to the user and fetching json
    fun messageStart() {
        val constraints = Constraints.Builder()
            .setRequiresCharging(true)
            .build()

        val jsonConstraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .setRequiresBatteryNotLow(true)
            .build()

        val workRequest = PeriodicWorkRequestBuilder<MessageWorker>(20, TimeUnit.MINUTES)
            .setInitialDelay(5, TimeUnit.SECONDS)
            .setConstraints(constraints)
            .build()

        val jsonWorkRequest = PeriodicWorkRequestBuilder<JsonWorker>(2, TimeUnit.DAYS)
            .setConstraints(jsonConstraints)
            .build()

        workManager.enqueue(workRequest)
        workManager.enqueue(jsonWorkRequest)
    }

    // Stop all messages from sending
    fun messageStop() {
        workManager.cancelAllWork()
    }
}
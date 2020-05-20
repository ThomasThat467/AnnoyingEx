package com.example.annoyingex.manager

import android.content.Context
import androidx.work.*
import com.example.annoyingex.MessageWorker
import java.util.concurrent.TimeUnit

class MessageWorkManager(private val context: Context) {
    private var workManager = WorkManager.getInstance(context)

    fun messageStart() {
        val constraints = Constraints.Builder()
            .setRequiresCharging(true)
            .build()

        val workRequest = PeriodicWorkRequestBuilder<MessageWorker>(20, TimeUnit.MINUTES)
            .setInitialDelay(5, TimeUnit.SECONDS)
            .setConstraints(constraints)
            .build()

        workManager.enqueue(workRequest)
    }

    fun messageStop() {
        workManager.cancelAllWork()
    }
}
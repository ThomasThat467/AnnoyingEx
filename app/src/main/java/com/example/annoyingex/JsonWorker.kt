package com.example.annoyingex

import android.content.Context
import android.util.Log
import androidx.work.*

class JsonWorker (context: Context, workParams: WorkerParameters): Worker(context, workParams) {
    private val app = (context as AnnoyingExApp)

    // Fetches the json
    override fun doWork(): Result {
        app.apiManager.fetchMessages(
            {
                Log.i("JsonWorker", "Fetch was successful")
            },
            {
                Log.i("JsonWorker", "Fetch was unsuccessful")
            }
        )

        return Result.success()
    }
}
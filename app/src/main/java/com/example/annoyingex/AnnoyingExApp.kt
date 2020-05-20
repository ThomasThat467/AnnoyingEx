package com.example.annoyingex

import android.app.Application
import com.example.annoyingex.manager.MessageWorkManager
import com.example.annoyingex.manager.ApiManager
import com.example.annoyingex.manager.MessageNotificationManager

class AnnoyingExApp: Application() {
    lateinit var apiManager: ApiManager
    lateinit var messageWorkManager: MessageWorkManager
    lateinit var messageNotificationManager: MessageNotificationManager
    var messageList: MutableList<String> = mutableListOf()

    override fun onCreate() {
        super.onCreate()

        apiManager = ApiManager(this)
        messageWorkManager = MessageWorkManager(this)
        messageNotificationManager = MessageNotificationManager(this)
    }
}
package com.example.annoyingex.manager

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.annoyingex.AnnoyingExApp
import com.example.annoyingex.R
import com.example.annoyingex.activity.MainActivity
import kotlin.random.Random

class MessageNotificationManager(private val context: Context) {

    private val notificationManagerCompat = NotificationManagerCompat.from(context)

    init {
        createNotificationChannel()
    }

    fun makeMessage() {
        val dealsIntent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }

        val pendingDealsIntent = PendingIntent.getActivity(context, 0, dealsIntent, PendingIntent.FLAG_UPDATE_CURRENT)

        val messageList = (context as AnnoyingExApp).messageList

        val notification = NotificationCompat.Builder(context, MESSAGE_CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("Don't Pick Up")
            .setContentText(messageList[Random.nextInt(messageList.size)])
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setCategory(NotificationCompat.CATEGORY_MESSAGE)
            .setContentIntent(pendingDealsIntent)
            .setAutoCancel(true)
            .build()

        notificationManagerCompat.notify(Random.nextInt(), notification)
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "My Dumb Ex"
            val descriptionText = "Seriously, can they get any more desperate?"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(MESSAGE_CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            notificationManagerCompat.createNotificationChannel(channel)
        }
    }

    companion object {
        const val MESSAGE_CHANNEL_ID = "MESSAGECHANNELID"
    }

}
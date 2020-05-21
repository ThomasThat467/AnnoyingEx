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

    // Creates notification of fake message
    fun makeMessage() {
        val messageList = (context as AnnoyingExApp).messageList
        val message = messageList[Random.nextInt(messageList.size)]

        val intent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            this.putExtra("message", message)
        }

        val pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)

        val notification = NotificationCompat.Builder(context, MESSAGE_CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("Don't Pick Up")
            .setContentText(message)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setCategory(NotificationCompat.CATEGORY_MESSAGE)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .build()

        notificationManagerCompat.notify(Random.nextInt(), notification)
    }


    // Makes notification channel to make notifications
    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "My Annoying Ex"
            val descriptionText = "Sends annoying messages"
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
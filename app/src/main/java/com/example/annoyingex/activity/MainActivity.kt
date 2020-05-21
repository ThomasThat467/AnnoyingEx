package com.example.annoyingex.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.annoyingex.R
import com.example.annoyingex.AnnoyingExApp
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val app = application as AnnoyingExApp

        // Store the messages in the app or add a default message
        app.apiManager.fetchMessages(
            { list ->
                app.messageList = list.messages as MutableList
            },
            {
                app.messageList.add("unable to retrieve message")
            }
        )

        button.setOnClickListener {
            app.messageWorkManager.messageStart()
        }
        button2.setOnClickListener {
            app.messageWorkManager.messageStop()
        }
    }
}

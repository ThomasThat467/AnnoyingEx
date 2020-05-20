package com.example.annoyingex.manager

import android.content.Context
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.annoyingex.model.MessageList
import com.google.gson.Gson


class ApiManager(context: Context) {
    private val queue = Volley.newRequestQueue(context)

    fun fetchMessages(initMessages: (MessageList) -> Unit, onError: (() -> Unit)? = null) {
        val songListURL = "https://raw.githubusercontent.com/echeeUW/codesnippets/master/ex_messages.json"

        val request = StringRequest(
            Request.Method.GET, songListURL,
            { response ->
                val gson = Gson()
                val allMessages = gson.fromJson(response, MessageList::class.java)

                initMessages(allMessages)
            },
            {
                onError?.invoke()
            }
        )

        queue.add(request)
    }
}
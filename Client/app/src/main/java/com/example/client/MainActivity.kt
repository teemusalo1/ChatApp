package com.example.client

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.mylinearlayout.*
import java.io.InputStream
import java.io.PrintStream
import java.net.Socket
import java.text.SimpleDateFormat
import java.time.LocalTime
import java.util.*
import kotlin.collections.ArrayList
import kotlin.concurrent.thread
import kotlin.system.measureTimeMillis

class MainActivity : AppCompatActivity(){
     val mydata =  mutableListOf<ChatMessage>()
     lateinit var adapter: MyRecyclerViewAdapter
    lateinit var chatServerConnector: ChatServerConnector


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       setContentView(R.layout.activity_main)
        val userName = intent.getStringExtra(EXTRA_MESSAGE)
        chatServerConnector = ChatServerConnector(this)
        Thread (chatServerConnector).start()


       recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = MyRecyclerViewAdapter(this, mydata)
        recyclerView.adapter = adapter



        val sendButton = findViewById<Button>(R.id.sendButton)
        val editText = findViewById<EditText>(R.id.editText)

        sendButton.setOnClickListener {
            var message = editText.getText().toString()

            if (message != "" ){
                chatServerConnector.newMessage(ChatMessage(message, username = userName))
            }
            editText.setText("")
        }
    }
}

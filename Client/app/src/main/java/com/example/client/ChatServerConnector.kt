package com.example.client

import android.util.Log
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import kotlinx.serialization.json.Json
import java.io.Closeable
import java.io.IOException
import java.io.InputStream
import java.io.PrintStream
import java.net.ServerSocket
import java.net.Socket
import java.util.*
import kotlin.concurrent.thread


class ChatServerConnector(val activity: MainActivity): Runnable {
    private lateinit var clientSocket: Socket
    private lateinit var scanner: InputStream
    private lateinit var printer: PrintStream
    var exit = false





    fun newMessage(message: ChatMessage){
        val messageAsJson = Json.stringify(ChatMessage.serializer(), message)// Outgoing messages
        try{
           thread{printer.println(messageAsJson)} }

        catch (e: IOException){
            Log.d("yhteys", "ERROR: ${e.message}")}

    }

    override fun run() {

        try {
            clientSocket = Socket("10.0.2.2",30001) // Opening the connection

            scanner = clientSocket.getInputStream()
            printer = PrintStream(clientSocket.getOutputStream(),true)

            val scanner = Scanner(clientSocket.getInputStream())
             printer.println("")

            while (true) {


                val   incoming = scanner.nextLine()// Incoming messages from the server

                Log.d("TAG", "$incoming")
                val newMessage = Json.parse(ChatMessage.serializer(), incoming)
                Log.d("parse", "$newMessage")



                activity.runOnUiThread(Runnable {
                    activity.adapter.add(newMessage)// adds incoming message to recyclerview

                })
            }

        }catch (e: IOException){
            Log.d("yhteys", "ERROR: ${e.message}")
        }
    }

    fun quit(){
        printer.close()
        scanner.close()
        exit = true
    }


}

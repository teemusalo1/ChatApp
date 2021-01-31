package com.example.client

import android.content.Context
import android.os.Message
import android.text.format.DateUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.mylinearlayout.view.*
import java.text.SimpleDateFormat
import java.util.*


class MyRecyclerViewAdapter(private val context: Context, private val mydata:  MutableList<ChatMessage>): RecyclerView.Adapter<MessageViewHolder>() {

    fun add(message: ChatMessage) {//
        mydata.add(message)
        notifyDataSetChanged()

    }


    override fun getItemCount(): Int {
        return mydata.size
    }
    override fun onCreateViewHolder(vg: ViewGroup, vt: Int): MessageViewHolder {

        return MyMessageViewHolder(LayoutInflater.from(context).inflate(R.layout.mylinearlayout, vg,false))//

    }

    override fun onBindViewHolder(vh: MessageViewHolder, pos: Int) {
      //  val message = mydata.get(pos)
        
       vh.itemView.findViewById<TextView>(R.id.timeText).text = mydata[pos].time.toString()
        vh.itemView.findViewById<TextView>(R.id.userText).text = mydata[ pos ].username
      vh.itemView.findViewById<TextView>(R.id.messageText).text = mydata[ pos ].message

    }

    inner class MyMessageViewHolder(view: View) : MessageViewHolder(view) {
        private var messageText: TextView = view.messageText
        private var userText: TextView = view.userText
        private var timeText: TextView = view.timeText

        override fun bind(message: ChatMessage) {
            messageText.text = message.message
            userText.text = message.username
            timeText.text = com.example.client.DateUtils.fromMillisToTimeString(message.time)

        }
    }
}
open class MessageViewHolder(view: View): RecyclerView.ViewHolder(view){
    open fun bind(message: ChatMessage) {}
}


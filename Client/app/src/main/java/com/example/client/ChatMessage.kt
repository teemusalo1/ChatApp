package com.example.client

import android.util.Log
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable
import java.util.*

@Serializable
 class  ChatMessage (val message: String, val username: String, var time: Long = Date().time) {

     override fun toString(): String {
        return "[$time] $username: $message"


}}

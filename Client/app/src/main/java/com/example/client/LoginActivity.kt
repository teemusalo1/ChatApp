package com.example.client

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import kotlinx.android.synthetic.main.activity_login.*

const val EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE"
class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val editText = findViewById<EditText>(R.id.editText2)


        button.setOnClickListener {
            val username = editText.text.toString()// takes the username from edittext and starts a new activity
            if (username != null){
            val intent = Intent(this, MainActivity::class.java).apply {
                putExtra(EXTRA_MESSAGE, username)
            }
            startActivity(intent)
        }}
    }
}

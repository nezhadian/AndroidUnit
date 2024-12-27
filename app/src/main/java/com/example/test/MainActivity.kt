package com.example.test

import LoginManager
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var statusTextView: TextView
    private val statusMessages = listOf(
        "Loading BMI activity",
        "Loading Factorial activity",
        "Loading Fibonacci activity",
        "Loading AI Blog activity",
        "Loading Shared Preferences",
        "Authentication...",
        "Navigating...",
    )
    private val handler = Handler(Looper.getMainLooper())
    private var currentIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        statusTextView = findViewById(R.id.splashStatus)
        updateStatusMessages()

    }

    private fun updateStatusMessages() {
        if (currentIndex < statusMessages.size) {
            statusTextView.text = statusMessages[currentIndex]
            currentIndex++
            var calcDelay = 499;
            handler.postDelayed({ updateStatusMessages() }, calcDelay.toLong())
        }
        else
        {
            handler.postDelayed({ GoToNextActivity() }, 1000);
        }
    }


    //navigate section
    private fun GoToNextActivity() {

        var loginManager = LoginManager(this);

        if(loginManager.IsLogin()){
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }
        else{
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }


    }
}
package com.example.test

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
        "Initializing application...",
        "Loading user preferences...",
        "Fetching latest updates...",
        "Connecting to the server...",
        "Verifying user credentials...",
        "Loading dashboard components...",
        "Syncing data with the cloud...",
        "Preparing your workspace...",
        "Fetching notifications...",
        "Loading assets...",
        "Setting up your profile...",
        "Establishing secure connection...",
        "Gathering system information...",
        "Loading modules...",
        "Checking for updates...",
        "Downloading resources...",
        "Compiling data...",
        "Initializing background services...",
        "Loading plugins...",
        "Fetching user data...",
        "Preparing user interface...",
        "Loading recent activity...",
        "Syncing with external services...",
        "Validating settings...",
        "Loading themes...",
        "Fetching content...",
        "Initializing analytics...",
        "Setting up notifications...",
        "Loading help resources...",
        "Preparing offline mode...",
        "Checking for compatibility...",
        "Loading language packs...",
        "Fetching user statistics...",
        "Initializing security protocols...",
        "Loading recent files...",
        "Syncing bookmarks...",
        "Preparing for first-time setup...",
        "Loading tutorial resources...",
        "Fetching system updates...",
        "Initializing payment gateway...",
        "Loading user achievements...",
        "Syncing calendar events...",
        "Preparing data backup...",
        "Loading recent projects...",
        "Fetching community updates...",
        "Initializing chat services...",
        "Loading user preferences...",
        "Syncing with social media...",
        "Preparing for data migration...",
        "Loading performance metrics...",
        "Fetching system diagnostics...",
        "Initializing user session...",
        "Loading accessibility features...",
        "Syncing device settings...",
        "Preparing for app launch...",
        "Loading configuration files...",
        "Fetching API data...",
        "Initializing user feedback system...",
        "Loading system resources...",
        "Syncing with third-party apps...",
        "Preparing for updates...",
        "Loading user-generated content...",
        "Fetching location data...",
        "Initializing search functionality...",
        "Loading multimedia assets...",
        "Syncing preferences across devices...",
        "Preparing for data analysis...",
        "Loading system notifications...",
        "Fetching user history...",
        "Initializing content delivery...",
        "Loading interactive elements...",
        "Syncing with cloud storage...",
        "Preparing for user onboarding...",
        "Loading performance enhancements...",
        "Fetching localization data...",
        "Initializing backup systems...",
        "Loading user guides...",
        "Syncing with external databases...",
        "Preparing for feature rollout...",
        "Loading system updates...",
        "Fetching user feedback...",
        "Initializing error reporting...",
        "Loading data visualizations...",
        "Syncing with project management tools...",
        "Preparing for user engagement...",
        "Loading security updates...",
        "Fetching system logs...",
        "Initializing user support...",
        "Loading community resources...",
        "Syncing with analytics platforms...",
        "Preparing for app optimization...",
        "Loading user preferences...",
        "Fetching integration settings...",
        "Initializing performance monitoring...",
        "Loading system configurations...",
        "Syncing with development tools...",
        "Preparing for user customization...",
        "Loading real-time data...",
        "Fetching system alerts...",
        "Application loaded successfully!"
    )
    private val handler = Handler(Looper.getMainLooper())
    private var currentIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        statusTextView = findViewById(R.id.splashStatus)
        updateStatusMessages()

    }

    private fun updateStatusMessages() {
        if (currentIndex < statusMessages.size) {
            statusTextView.text = statusMessages[currentIndex]
            currentIndex++
            var calcDelay = 20;
            handler.postDelayed({ updateStatusMessages() }, calcDelay.toLong())
        }
        else
        {
            handler.postDelayed({ GoToNextActivity() }, 280);
        }
    }

    private fun GoToNextActivity() {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
    }
}
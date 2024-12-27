package com.example.test

import LoginManager
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val btn = findViewById<Button>(R.id.btnLogin)
        val txtUsername = findViewById<EditText>(R.id.txtLoginUser)
        val txtPassword = findViewById<EditText>(R.id.txtLoginPass)
        val txtStatus = findViewById<TextView>(R.id.txtLoginStatus)

        btn.setOnClickListener {

            val username = txtUsername.text.toString()
            val password = txtPassword.text.toString()

            if (username != "admin") {
                txtStatus.text = "invalid username (use \"admin\")"
                return@setOnClickListener;
            }

            if (password != "12345678") {
                txtStatus.text = "invalid password (use \"12345678\")"
                return@setOnClickListener;
            }

            val loginMgr = LoginManager(this)
            loginMgr.Login(username)

            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)

        }
    }
}
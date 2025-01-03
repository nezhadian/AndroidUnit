package com.example.test

import Item
import ItemAdapter
import LoginManager
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class HomeActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)


        //######################### set title #########################
        val txtTitle = findViewById<TextView>(R.id.txtHomeTitle)
        val loginMgr = LoginManager(this)
        txtTitle.text = loginMgr.GetUsername()




        //######################### logout button #########################
        val btnLogout = findViewById<Button>(R.id.btnHomeLogout);
        btnLogout.setOnClickListener{
            loginMgr.Logout();

            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)

            finish()
        }


        //######################### set items #########################
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val itemList = listOf(
            Item("AI Blog (USING SQL)", AIBlog::class.java),
            Item("BMI", BMI::class.java),
            Item("Calculator", CalculatorActivity::class.java),
            Item("Fib and Fac", SquencesActivity::class.java),
            Item("SMS", SMSActivity::class.java),
        )

        val itemAdapter = ItemAdapter(itemList, this)
        recyclerView.adapter = itemAdapter




    }
}
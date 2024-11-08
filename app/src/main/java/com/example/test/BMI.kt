package com.example.test

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class BMI : AppCompatActivity() {

    private lateinit var txtHeight: EditText
    private lateinit var txtWeight: EditText
    private lateinit var btnCalculate: Button
    private lateinit var txtResult: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_bmi)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Initialize views
        txtHeight = findViewById(R.id.txtHeight);
        txtWeight = findViewById(R.id.txtWeight);
        btnCalculate = findViewById(R.id.btnCalculate);
        txtResult = findViewById(R.id.txtResult);


        // Set up button click listener
        btnCalculate.setOnClickListener { calculateResult() }

    }

    private fun calculateResult() {

        // Get input values
        val heightStr = txtHeight.text.toString()
        val weightStr = txtWeight.text.toString()


        // Check if inputs are valid
        if (!heightStr.isEmpty() && !weightStr.isEmpty()) {
            // Parse input values
            val height = heightStr.toFloat() / 100 // Convert cm to meters
            val weight = weightStr.toFloat()

            // Calculate BMI (or any other calculation you want)
            val bmi = weight / (height * height)

            val minNormalWeight = 18.5 * (height * height)
            val maxNormalWeight = 24.9 * (height * height)

            val advice = getAdviceMessage(bmi)

            // Display result
            txtResult.text = String.format(
                "شاخص توده بدنی (BMI): %.2f\n" +
                        "وزن ایده‌آل: %.2f کیلوگرم - %.2f کیلوگرم\n" +
                        "نکته: %s",
                bmi, minNormalWeight, maxNormalWeight, advice
            )
        } else {
            txtResult.text = "لطفا مقادیر را وارد کنید"
        }
    }

    private fun getAdviceMessage(bmi: Float): String {
        return when {
            bmi < 18.5 -> "شما کمبود وزن دارید. بهتر است با پزشک مشورت کنید."
            bmi in 18.5..24.9 -> "وزن شما نرمال است. همینطور ادامه بدید!"
            bmi in 25.0..29.9 -> "شما اضافه وزن دارید. بهتر است رژیم غذایی و فعالیت بدنی خود را بررسی کنید."
            else -> "شما چاق هستید. توصیه می‌شود با پزشک مشورت کنید."
        }
    }
}
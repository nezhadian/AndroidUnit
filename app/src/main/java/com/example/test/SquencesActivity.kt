package com.example.test
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SquencesActivity : AppCompatActivity() {

    private lateinit var tvFibonacci: TextView
    private lateinit var tvFactorial: TextView
    private lateinit var txtSeq: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_squences)

        tvFibonacci = findViewById(R.id.tvFibonacci)
        tvFactorial = findViewById(R.id.tvFactorial)
        txtSeq = findViewById(R.id.txtSeq)

        // Add TextWatcher to EditText
        txtSeq.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // Get the input text and convert it to a number
                val input = s?.toString() ?: ""
                if (input.isNotEmpty()) {
                    try {
                        val number = input.toLong()
                        // Calculate Fibonacci and Factorial
                        val fibonacci = calculateFibonacci(number)
                        val factorial = calculateFactorial(number)
                        // Update TextViews
                        tvFibonacci.text = fibonacci.toString()
                        tvFactorial.text = factorial.toString()
                    } catch (e: NumberFormatException) {
                        // Handle invalid input
                        tvFibonacci.text = "0"
                        tvFactorial.text = "0"
                    }
                } else {
                    // Clear TextViews if input is empty
                    tvFibonacci.text = "0"
                    tvFactorial.text = "0"
                }
            }

            override fun afterTextChanged(s: Editable?) {}
        })
    }

    // Method to calculate Fibonacci number
    private fun calculateFibonacci(n: Long): Long {
        if (n <= 0) return 0
        if (n == 1L) return 1

        var a = 0L
        var b = 1L
        for (i in 2..n) {
            val temp = a + b
            a = b
            b = temp
        }
        return b
    }

    // Method to calculate Factorial
    private fun calculateFactorial(n: Long): Long {
        if (n <= 0) return 1

        var result = 1L
        for (i in 1..n) {
            result *= i
        }
        return result
    }
}
package com.example.test

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class CalculatorActivity : AppCompatActivity() {

    private lateinit var tvResult : TextView
    private lateinit var tvInput : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculator)

        addAppendNumberClickHandler(
            arrayOf(
            R.id.btn0,
            R.id.btn1,
            R.id.btn2,
            R.id.btn3,
            R.id.btn4,
            R.id.btn5,
            R.id.btn6,
            R.id.btn7,
            R.id.btn8,
            R.id.btn9)
        )

        addOperandClickHandler(
            arrayOf(
                R.id.btnAdd,
                R.id.btnDivide,
                R.id.btnMultiply,
                R.id.btnSubtract))


        findViewById<Button>(R.id.btnDot).setOnClickListener{appendDot()}

        tvResult = findViewById(R.id.tvResult)
        tvInput = findViewById(R.id.tvInput);
        // Clear button
        findViewById<Button>(R.id.btnClear).setOnClickListener { clear() }

        // Equals button
        findViewById<Button>(R.id.btnEquals).setOnClickListener { calculateResult() }
    }

    private fun addOperandClickHandler(ids: Array<Int>) {
        for(i in ids){
            findViewById<Button>(i).setOnClickListener{ view ->
                val btn = view as Button
                appendOperator(btn.tag.toString())
            }
        }
    }

    private fun addAppendNumberClickHandler(ids: Array<Int>) {
        for(i in ids){
            findViewById<Button>(i).setOnClickListener{ view ->
                val btn = view as Button
                appendNumber(btn.tag.toString())
            }
        }
    }



    fun appendDot() {
        val input = tvInput.text.toString()

        // If the input is empty, append "0."
        if (input.isEmpty()) {
            tvInput.append("0.")
            return
        }

        // Check if the last character is an operator
        val lastChar = input.last()
        if (lastChar in setOf('+', '-', '*', '/')) {
            tvInput.append("0.")
            return
        }

        // Check if the last character is already a dot
        if (lastChar == '.') {
            return
        }

        // Iterate from the end to find the last operand
        var hasDotInCurrentOperand = false
        for (i in input.length - 1 downTo 0) {
            val char = input[i]
            if (char == '.') {
                hasDotInCurrentOperand = true
                break
            }
            // Stop if we encounter an operator
            if (char in setOf('+', '-', '*', '/')) {
                break
            }
        }

        // If the current operand already has a dot, do not add another
        if (hasDotInCurrentOperand) {
            return
        }

        // Otherwise, append the dot
        tvInput.append(".")
    }

    private fun appendNumber(number: String) {
        tvInput.append(number)
    }

    private fun appendOperator(operator: String) {
        val lastChar = tvInput.text.last()

        if(lastChar == '.')
        {
            tvInput.append("0");
            tvInput.append(operator)
            return;
        }

        if(lastChar.isDigit()){
            tvInput.append(operator)
        }

    }

    private fun calculateResult() {

    }

    private fun clear() {
        tvInput.text = "";
    }
}
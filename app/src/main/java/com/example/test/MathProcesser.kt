package com.example.test

import java.util.Stack

class MathProcessor {

    // Convert infix equation to postfix notation
    private fun infixToPostfix(equation: String): String {
        val output = StringBuilder()
        val stack = Stack<Char>()
        val precedence = mapOf('+' to 1, '-' to 1, '*' to 2, '/' to 2)

        var i = 0
        while (i < equation.length) {
            val char = equation[i]

            when {
                char.isDigit() || char == '.' -> {
                    // Add digits and dots to output
                    output.append(char)
                    i++
                }
                else -> {
                    // Handle operators
                    output.append(' ')
                    while (stack.isNotEmpty() && precedence[char]!! <= precedence[stack.peek()]!!) {
                        output.append(stack.pop()).append(' ')
                    }
                    stack.push(char)
                    i++
                }
            }
        }

        // Pop all remaining operators from the stack
        while (stack.isNotEmpty()) {
            output.append(' ').append(stack.pop())
        }

        return output.toString().trim()
    }

    // Evaluate postfix expression
    private fun evaluatePostfix(postfix: String): Double {
        val stack = Stack<Double>()
        val tokens = postfix.split(" ")

        for (token in tokens) {
            when {
                token.isBlank() -> continue // Skip empty tokens
                token.toDoubleOrNull() != null -> stack.push(token.toDouble()) // Push numbers to stack
                else -> {
                    // Handle operators
                    val operand2 = stack.pop()
                    val operand1 = stack.pop()
                    val result = when (token) {
                        "+" -> operand1 + operand2
                        "-" -> operand1 - operand2
                        "*" -> operand1 * operand2
                        "/" -> operand1 / operand2
                        else -> throw IllegalArgumentException("Invalid operator: $token")
                    }
                    stack.push(result)
                }
            }
        }

        return stack.pop()
    }

    // Public method to calculate the result of an equation
    fun calculateEquation(equation: String): Double {
        val postfix = infixToPostfix(equation)
        return evaluatePostfix(postfix)
    }
}
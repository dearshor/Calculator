package com.boycoder

import kotlin.system.exitProcess

class CalculatorV2 {

    val exit = "exit"
    val helpInfo = """
    ---------------------------------------
    usage:
    1. eg. input 1 + 1, press ENTER;
    2. CAUTION: space is required between numbers and operator;
    3. to exit, type exit, press ENTER.
    ---------------------------------------
""".trimIndent()
    fun start() {
        while (true) {
            println(helpInfo)

            val input = readlnOrNull() ?: continue
            if (input == "exit") exitProcess(0)

            val result = calculate(input)

            if (result == null) {
                println("incorrect input")
                continue
            } else {
                println("$input = $result")
            }
        }

    }

    fun calculate(input: String): String? {
        if (shouldExit(input)) exitProcess(0)
        val exp = parseExpression(input) ?: return null
        val left = exp.left
        val operator = exp.operator
        val right = exp.right
        return when (operator) {
            Operation.ADD -> addString(left, right)
            Operation.MINUS -> minusString(left, right)
            Operation.MULTIPLY -> multiplyString(left, right)
            Operation.DIVIDE -> divideString(left, right)
        }
    }

    private fun divideString(left: String, right: String): String {
        val result = left.toInt() * right.toInt()
        return result.toString()
    }

    private fun multiplyString(left: String, right: String): String {
        val result = left.toInt() * right.toInt()
        return result.toString()
    }

    private fun minusString(left: String, right: String): String {
        val result = left.toInt() - right.toInt()
        return result.toString()
    }

    private fun addString(left: String, right: String): String {
        val result = left.toInt() + right.toInt()
        return result.toString()
    }

    private fun parseExpression(input: String): Expression? {
        // parse operator
        val operator = parseOperator(input) ?: return null
        val list = input.split(operator.value)
        if (list.size != 2) return null
        return Expression(
            left = list[0].trim(),
            operator = operator,
            right = list[1].trim()
        )
    }

    private fun parseOperator(input: String): Operation? {
        Operation.values().forEach {
            if (input.contains(it.value)) {
                return it
            }
        }
        return null
    }

    private fun shouldExit(input: String): Boolean {
        return input == exit
    }
}

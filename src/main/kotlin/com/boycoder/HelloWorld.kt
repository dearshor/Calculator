package com.boycoder

import kotlin.system.exitProcess

val helpInfo = """
    ---------------------------------------
    usage:
    1. eg. input 1 + 1, press ENTER;
    2. CAUTION: space is required between numbers and operator;
    3. to exit, type exit, press ENTER.
    ---------------------------------------
""".trimIndent()
fun main() {
    while (true) {
        println(helpInfo)

        val input = readlnOrNull() ?: continue
        if (input == "exit") exitProcess(0)

        val inputList = input.split(" ")
        val result = calculate(inputList)

        if (result == null) {
            println("incorrect input")
            continue
        } else {
            println("$input = $result")
        }
    }
}

private fun calculate(inputList: List<String>): Int? {
    if (inputList.size != 3) return null

    val left = inputList[0].toInt()
//    val operator = Operation.valueOf(inputList[1])
    val operator = when (inputList[1]) {
        "+" -> Operation.ADD
        "-" -> Operation.MINUS
        "*" -> Operation.MULTIPLY
        "/" -> Operation.DIVIDE
        else -> return null
    }
    val right = inputList[2].toInt()
    return when (operator) {
        Operation.ADD -> left + right
        Operation.MINUS -> left - right
        Operation.MULTIPLY -> left * right
        Operation.DIVIDE -> left / right
    }
}

enum class Operation(val value: String) {
    ADD("+"),
    MINUS("-"),
    MULTIPLY("*"),
    DIVIDE("/")
}

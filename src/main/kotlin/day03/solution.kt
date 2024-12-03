package org.example.day03

import kotlin.math.exp

fun addingUpParsedMultiplication(inputRows : List<String>) : Int {
    return inputRows.sumOf { iterateCollectMultiplication(it, listOf()).sumOf { p -> p.first * p.second } }
}

fun iterateCollectMultiplication(str: String, acc: List<Pair<Int, Int>>) : List<Pair<Int, Int>> {
    if (str.length < 8) return acc
    val mulPos: Int = str.indexOf("mul")
    if (str[mulPos+3] != '(') return iterateCollectMultiplication(str.substring(mulPos+1), acc)
    val expression: String = str.substring(mulPos).takeWhile { it != ')' } + ')'
    if (expression.matches("mul\\(\\d{1,3},\\d{1,3}\\)".toRegex())) {
        return iterateCollectMultiplication(
            str.substring(mulPos+expression.length),
            acc + stripNumbersFromExpression(expression)
        )
    }
    return iterateCollectMultiplication(str.substring(mulPos+1), acc)
}

fun stripNumbersFromExpression(str: String) : Pair<Int, Int> {
    val (first, second) = str.replace("mul(", "").replace(")", "")
        .split(",").map { it.toInt() }
    return first to second
}
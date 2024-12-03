package org.example.day03

class InstructionParser(val instruction: String) {

    private val operatorValue : String = "mul"
    private val expressionRegex : Regex = "mul\\(\\d{1,3},\\d{1,3}\\)".toRegex()

    fun iterateCollectMultiplicationIndexed(cursor: Int, acc: List<IndexedPair>) : List<IndexedPair> {
        val chunk = instruction.substring(cursor)
        if (chunk.length < 8) return acc
        val operatorPosition: Int = chunk.indexOf(operatorValue)
        val newCursor = cursor + operatorPosition
        if (instruction[newCursor + operatorValue.length] != '(')
            return iterateCollectMultiplicationIndexed(newCursor + 1, acc)
        val expression: String = instruction.substring(newCursor).takeWhile { it != ')' } + ')'
        if (expression.matches(expressionRegex)) {
            return iterateCollectMultiplicationIndexed(
                newCursor + expression.length,
                acc + IndexedVal(stripNumbersFromExpression(expression), newCursor)
            )
        }
        return iterateCollectMultiplicationIndexed(newCursor + 1, acc)
    }

    private fun stripNumbersFromExpression(str: String) : Pair<Int, Int> {
        val (first, second) = str.replace("mul(", "").replace(")", "")
            .split(",").map { it.toInt() }
        return first to second
    }

}
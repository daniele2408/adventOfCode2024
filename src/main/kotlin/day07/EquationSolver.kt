package org.example.day07

import java.math.BigInteger

class EquationSolver(val expectedResult: BigInteger, private val operands: List<BigInteger>) {
    companion object {
        fun from(inputRows: String) : EquationSolver {
            val (res, operands) = inputRows.split(':')
            return EquationSolver(
                BigInteger(res),
                operands.trim().split(" ")
                    .map { BigInteger(it) }
            )
        }
    }

    fun solve() : Boolean {
        val rest = operands.subList(1, operands.size)
        return isThereAnyCorrectCombinations(
            rest,
            operands.first()
        )
    }

    fun solveWithPipe() : Boolean {
        val rest = operands.subList(1, operands.size)
        return isThereAnyCorrectCombinationsWithPipeConcat(
            rest,
            operands.first()
        )
    }

    private fun isThereAnyCorrectCombinations(operands: List<BigInteger>, acc: BigInteger) : Boolean {
        if (operands.isEmpty()) return acc == expectedResult
        val rest = operands.subList(1, operands.size)
        return isThereAnyCorrectCombinations(
            rest,
            operands.first() * acc
        ) || isThereAnyCorrectCombinations(
            rest,
            operands.first() + acc
        )
    }

    private fun isThereAnyCorrectCombinationsWithPipeConcat(operands: List<BigInteger>, acc: BigInteger) : Boolean {
        if (operands.isEmpty()) return acc == expectedResult
        val rest = operands.subList(1, operands.size)
        return isThereAnyCorrectCombinationsWithPipeConcat(
            rest,
            operands.first() * acc
        ) || isThereAnyCorrectCombinationsWithPipeConcat(
            rest,
            operands.first() + acc
        ) || isThereAnyCorrectCombinationsWithPipeConcat(
            rest,
            acc pipeConcat operands.first()
        )
    }

    private infix fun BigInteger.pipeConcat(other: BigInteger) : BigInteger {
        return BigInteger(this.toString() + other.toString())
    }
}
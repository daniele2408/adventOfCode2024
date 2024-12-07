package org.example.day07

import java.math.BigInteger

fun computeTotalCalibrationResult(inputRows: List<String>) : BigInteger {
    return inputRows.map { EquationSolver.from(it) }.filter { it.solve() }.sumOf { it.expectedResult }
}

fun computeTotalCalibrationResultWithPipe(inputRows: List<String>) : BigInteger {
    return inputRows.map { EquationSolver.from(it) }.filter { it.solveWithPipe() }.sumOf { it.expectedResult }
}
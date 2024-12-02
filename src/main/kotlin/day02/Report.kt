package org.example.day02

import kotlin.math.abs

data class Report(val report: List<Int>) {
    fun isSafe(): Boolean {
        val monotonousDec = report.zipWithNext().all { (a, b) -> a > b && (abs(a-b) in (1..3)) }
        val monotonousInc = report.zipWithNext().all { (a, b) -> a < b && (abs(a-b) in (1..3)) }
        return monotonousInc || monotonousDec
    }

    fun isSafeUsingProblemDampener() : Boolean {
        val anySafeDampeningProblem = (0..report.size)
            .map { report.removeIthElFromList(it) }.any { Report(it).isSafe() }
        return isSafe() || anySafeDampeningProblem
    }

    private fun <T> List<T>.removeIthElFromList(ith: Int) : List<T> {
        require(this.size >= ith)
        if (this.size == ith) return this.subList(0, this.size-1)
        return this.subList(0, ith) + this.subList(ith + 1, this.size)
    }

    companion object {
        fun from(inputStr: String) : Report {
            return Report(inputStr.split(" ").map { it.toInt() })
        }
    }
}

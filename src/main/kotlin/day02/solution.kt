package org.example.day02

fun howManySafeReports(inputRows: List<String>) : Int {
    val reports = inputRows.map { Report.from(it) }.toList()
    return reports.count { it.isSafe() }
}

fun howManySafeReportsWithProblemDampener(inputRows: List<String>) : Int {
    val reports = inputRows.map { Report.from(it) }.toList()
    return reports.count { it.isSafeUsingProblemDampener() }
}

package org.example.day13

fun howManyTokens(inputRows: List<String>) : Long {
    val clawMachines = clawMachinesCreate(inputRows)
    val res = clawMachines.mapNotNull { it.solve() }.sumOf { it.first * 3 + it.second }
    return res
}

fun howManyTokensMoar(inputRows: List<String>) : Long {
    val clawMachines = clawMachinesCreate(inputRows, 10000000000000)
    val res = clawMachines.mapNotNull { it.solve() }.sumOf { it.first * 3 + it.second }
    return res
}

fun clawMachinesCreate(inputRows: List<String>, startOff: Long = 0) : List<ClawMachine> {
    return inputRows.chunked(4).map { ClawMachine.from(it, startOff) }
}
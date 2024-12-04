package org.example.day04

import org.example.model.GridCoordValue
import org.example.model.GridSet

fun GridSet<Char>.scanDirection(gridCoordValue: GridCoordValue<Char>?, direction: GridSet.Direction, acc: String) : String {
    if (gridCoordValue == null || acc.length == 4) return acc
    val next = this.getNext(gridCoordValue, direction)
    return scanDirection(next, direction, acc + (next?.value ?: ""))
}

fun GridSet<Char>.scanXMAS(gridCoordValue: GridCoordValue<Char>) : Boolean {
    if (gridCoordValue.value != 'A') return false
    val nextNW = getNext(gridCoordValue, GridSet.Direction.NW)?.value.toString() ?: ""
    val nextSW = getNext(gridCoordValue, GridSet.Direction.SW)?.value.toString() ?: ""
    val nextNE = getNext(gridCoordValue, GridSet.Direction.NE)?.value.toString() ?: ""
    val nextSE = getNext(gridCoordValue, GridSet.Direction.SE)?.value.toString() ?: ""

    val values = listOf(nextNW, nextNE, nextSW, nextSE).joinToString("")

    return when (values) {
        "MMSS", "MSMS", "SMSM", "SSMM" -> true
        else -> false
    }
}

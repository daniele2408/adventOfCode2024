package org.example.day04

import org.example.model.Grid
import org.example.model.GridCoordValue
import org.example.model.GridSparse

fun Grid<Char>.scanDirection(gridCoordValue: GridCoordValue<Char>?, direction: Grid.Direction, acc: String) : String {
    if (gridCoordValue == null || acc.length == 4) return acc
    val next = this.getNext(gridCoordValue, direction)
    return scanDirection(next, direction, acc + (next?.value ?: ""))
}

fun Grid<Char>.scanXMAS(gridCoordValue: GridCoordValue<Char>) : Boolean {
    if (gridCoordValue.value != 'A') return false
    val nextNW = getNext(gridCoordValue, Grid.Direction.NW)?.value.toString() ?: ""
    val nextSW = getNext(gridCoordValue, Grid.Direction.SW)?.value.toString() ?: ""
    val nextNE = getNext(gridCoordValue, Grid.Direction.NE)?.value.toString() ?: ""
    val nextSE = getNext(gridCoordValue, Grid.Direction.SE)?.value.toString() ?: ""

    val values = listOf(nextNW, nextNE, nextSW, nextSE).joinToString("")

    return when (values) {
        "MMSS", "MSMS", "SMSM", "SSMM" -> true
        else -> false
    }
}

package org.example.day04

import arrow.core.memoize
import org.example.model.GridCoordValue
import org.example.model.GridSet

fun howManyTimesXmasOccurs(inputRows: List<String>) : Int {
    val gridSet: GridSet<Char> = GridSet.fromInput(inputRows.map { row -> row.map { it } })

    return gridSet.data.filter { it.value == 'X' }.sumOf { memoizedcountWordsFromCoord(gridSet, it) }
}

fun howManyTimesCrossXmasOccurs(inputRows: List<String>) : Int {
    val gridSet: GridSet<Char> = GridSet.fromInput(inputRows.map { row -> row.map { it } })
    return gridSet.data.filter { it.value == 'A' }.sumOf { memoizedcountXmasFromCoord(gridSet, it) }
}

fun countWordsFromCoord(grid: GridSet<Char>, gridCoordValue: GridCoordValue<Char>) : Int {
    return GridSet.Direction.entries.map { grid.scanDirection(gridCoordValue, it, gridCoordValue.value.toString()) }.count { it == "XMAS" }
}

fun countXmasFromCoord(grid: GridSet<Char>, gridCoordValue: GridCoordValue<Char>) : Int {
    return if (grid.scanXMAS(gridCoordValue)) 1 else 0
}

val memoizedcountWordsFromCoord = ::countWordsFromCoord.memoize()
val memoizedcountXmasFromCoord = ::countXmasFromCoord.memoize()
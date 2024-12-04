package org.example.day04

import arrow.core.memoize
import org.example.model.Grid
import org.example.model.GridCoordValue
import org.example.model.GridDense

fun howManyTimesXmasOccurs(inputRows: List<String>) : Int {
    val gridSparse: GridDense<Char> = GridDense.fromInput(inputRows.map { row -> row.map { it } })

    return gridSparse.dataAsSeq().filter { it.value == 'X' }.sumOf { memoizedcountWordsFromCoord(gridSparse, it) }
}

fun howManyTimesCrossXmasOccurs(inputRows: List<String>) : Int {
    val gridSparse: GridDense<Char> = GridDense.fromInput(inputRows.map { row -> row.map { it } })
    return gridSparse.dataAsSeq().filter { it.value == 'A' }.sumOf { memoizedcountXmasFromCoord(gridSparse, it) }
}

fun countWordsFromCoord(grid: GridDense<Char>, gridCoordValue: GridCoordValue<Char>) : Int {
    return Grid.Direction.entries.map { grid.scanDirection(gridCoordValue, it, gridCoordValue.value.toString()) }.count { it == "XMAS" }
}

fun countXmasFromCoord(grid: GridDense<Char>, gridCoordValue: GridCoordValue<Char>) : Int {
    return if (grid.scanXMAS(gridCoordValue)) 1 else 0
}

val memoizedcountWordsFromCoord = ::countWordsFromCoord.memoize()
val memoizedcountXmasFromCoord = ::countXmasFromCoord.memoize()
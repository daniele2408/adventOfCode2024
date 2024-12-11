package org.example.day10

import org.example.model.Grid
import org.example.model.GridCoordValue
import org.example.model.GridDense

fun sumScores(inputRows: List<String>) : Int {
    val grid = GridDense.fromInput(inputRows.map { it.map { e -> e.digitToInt() } })
    val startingPoints = grid.dataAsSeq().filter { it.value == 0 }.map { it }
    return startingPoints.sumOf {
        val paths = grid.findPath(it, listOf(it))
        paths.distinctBy { p -> p.last() }.count()
    }
}

fun sumRatings(inputRows: List<String>) : Int {
    val grid = GridDense.fromInput(inputRows.map { it.map { e -> e.digitToInt() } })
    val startingPoints = grid.dataAsSeq().filter { it.value == 0 }.map { it }
    return startingPoints.sumOf {
        grid.findPath(it, listOf(it)).count()
    }
}

fun GridDense<Int>.findPath(startingPoint: GridCoordValue<Int>, path: List<GridCoordValue<Int>>) : Set<List<GridCoordValue<Int>>> {

    if (startingPoint.value == 9) return setOf(path)
    return sequenceOf(Grid.Direction.S, Grid.Direction.N, Grid.Direction.E, Grid.Direction.W)
        .mapNotNull { this.getNext(startingPoint, it) }
        .filter { it.value - startingPoint.value == 1 }
        .flatMap { findPath(it, path + listOf(it)) }.toSet()
}

fun GridDense<Int>.print(filterPath: Collection<GridCoordValue<Int>>) : String {
    return (0..<nRow).map { idxRow -> (0..<nCol).map { idxCol ->
        val cell = get(idxCol, idxRow)!!
        if (cell in filterPath) cell.value.toString()
        else "."
    }.joinToString("") }.joinToString("\n")
}
package org.example.day06

import org.example.model.*
import kotlin.math.max

fun createGridDenseStuff(inputRows: List<String>) : GridDense<Stuff> {
    val data = inputRows
        .mapIndexed { idxRow, row ->
            row.mapIndexed { idxCol, cell ->
                GridCoordValue(idxRow, idxCol, Stuff.from(cell.toString()))
            }.toList()
        }.toList()
    return GridDense(data)
}

fun GridDense<Stuff>.lastEmptySpaceFromPov(gridCoord: GridCoord, direction: Grid.Direction) : GridCoord? {
    val slice = sliceSectionUntilObstacleFrom(gridCoord, direction)
    if (slice.isNotEmpty()) {
        val lastCell = slice.last()
        if (lastCell.row == 0 || lastCell.col == 0 || lastCell.row == (nRow-1) || lastCell.col == (nCol-1)) return null
        else return lastCell.justCoord()
    }
    return gridCoord
}

fun GridDense<Stuff>.sliceSectionUntilObstacleFrom(gridCoord: GridCoord, direction: Grid.Direction) : List<GridCoordValue<Stuff>> {
    return when (direction) {
        Grid.Direction.N -> sliceCol(0, max(gridCoord.row, 0), gridCoord.col).reversed().takeWhile { it.value != Obstacle }
        Grid.Direction.S -> sliceCol(gridCoord.row, nRow, gridCoord.col).takeWhile { it.value != Obstacle }
        Grid.Direction.E -> sliceRow(gridCoord.col, nCol, gridCoord.row).takeWhile { it.value != Obstacle }
        Grid.Direction.W -> sliceRow(0, max(gridCoord.col, 0), gridCoord.row).reversed().takeWhile { it.value != Obstacle }
        else -> throw RuntimeException("Direction $direction not supported")
    }
}

fun GridDense<Stuff>.patrol(guard: Guard) : Guard? {
    val lastEmptyCell = this.lastEmptySpaceFromPov(guard.gridCoord, guard.direction) ?: return null
    return Guard(lastEmptyCell, guard.lookRight())
}

fun GridDense<Stuff>.addObstacleToGrid(gridCoord: GridCoord) : GridDense<Stuff> {
    if (this.isOutside(gridCoord)) throw RuntimeException("coords $gridCoord out of bound, can't add it to grid")
    if (this.get(gridCoord)!!.value == Obstacle) throw RuntimeException("can't add obstacle to $gridCoord, already full")
    val newData: List<List<GridCoordValue<Stuff>>> = this.data.mapIndexed { idxRow, row ->
        row.mapIndexed { idxCol, cel ->
            if (gridCoord.row == idxRow && gridCoord.col == idxCol) GridCoordValue(idxRow, idxCol, Stuff.from("O"))
            else cel
        }.toList()
    }.toList()
    return GridDense(newData)
}

fun GridDense<Stuff>.printAll(guard: Guard, obstacles: Set<GridCoord>) : String {
    return data.map { row -> row.map { cell ->
        if (cell.justCoord() == guard.gridCoord) {
            guard.printDirection()
        } else if (cell.justCoord() in obstacles) {
            "O"
        } else {
            if (cell.value is Empty) "."
            else "#"
        }
    }.joinToString("") }.joinToString("\n")
}

fun GridDense<Stuff>.printAll(guard: Guard, path: List<Pair<GridCoord, Grid.Direction>>, obstacles: Set<GridCoord>) : String {
    return data.map { row -> row.map { cell ->
        if (cell.justCoord() == guard.gridCoord) {
            guard.printDirection()
        } else if (path.any { it.first == cell.justCoord() }) {
            when (path.first { it.first == cell.justCoord() }.second) {
                Grid.Direction.N, Grid.Direction.S -> "|"
                else -> "-"
            }
        } else if (cell.justCoord() in obstacles) {
            "O"
        } else {
            if (cell.value is Empty) "."
            else "#"
        }
    }.joinToString("") }.joinToString("\n")
}

fun GridDense<Stuff>.printAround(guard: Guard, path: List<Pair<GridCoord, Grid.Direction>>, added: Set<GridCoord>) : String {
    val minRow = path.minBy { it.first.row }.first.row
    val maxRow = path.maxBy { it.first.row }.first.row
    val minCol = path.minBy { it.first.col }.first.col
    val maxCol = path.maxBy { it.first.col }.first.col

    val topCorner = this.getNextUntil(this.get(GridCoord(minRow, minCol))!!, Grid.Direction.NW, 1)
    val bottomCorner = this.getNextUntil(this.get(GridCoord(maxRow, maxCol))!!, Grid.Direction.SE, 1)

    val gridCoord = guard.gridCoord

    return (topCorner.row .. bottomCorner.row).map { idxRow -> (topCorner.col .. bottomCorner.col).map {
            idxCol -> this.get(idxCol, idxRow)
    }.filterNotNull().map { cell ->
        if (cell.justCoord() == gridCoord) {
            guard.printDirection()
        } else if (path.any { it.first == cell.justCoord() }) {
            when (path.first { it.first == cell.justCoord() }.second) {
                Grid.Direction.N, Grid.Direction.S -> "|"
                else -> "-"
            }
        } else if (cell.justCoord() in added) {
            "O"
        } else {
            if (cell.value is Empty) "."
            else "#"
        }
    }.joinToString("")}.joinToString("\n")

}

fun GridDense<Stuff>.printAround(guard: Guard, radius: Int) : String {
    val gridCoord = guard.gridCoord
    val topCorner = this.getNextUntil(this.get(gridCoord)!!, Grid.Direction.NW, radius)
    val bottomCorner = this.getNextUntil(this.get(gridCoord)!!, Grid.Direction.SE, radius)

    return (topCorner.row .. bottomCorner.row).map { idxRow -> (topCorner.col .. bottomCorner.col).map {
        idxCol -> this.get(idxCol, idxRow)
    }.filterNotNull().map { cell ->
        if (cell.justCoord() == gridCoord) {
            guard.printDirection()
        } else {
            if (cell.value is Empty) "."
            else "#"
        }
    }.joinToString("")}.joinToString("\n")
}
package org.example.model

import kotlin.math.abs

data class GridCoord(val row: Int, val col: Int) {
    fun getDirectionTo(other: GridCoord) : Grid.Direction {
        val rowDiff = this.row - other.row
        val colDiff = this.col - other.col
        return when {
            rowDiff < 0 && colDiff == 0 -> Grid.Direction.S
            rowDiff > 0 && colDiff == 0 -> Grid.Direction.N
            rowDiff == 0 && colDiff < 0 -> Grid.Direction.E
            rowDiff == 0 && colDiff > 0 -> Grid.Direction.W
            rowDiff > 0 && colDiff > 0 -> Grid.Direction.NW
            rowDiff > 0 && colDiff < 0 -> Grid.Direction.NE
            rowDiff < 0 && colDiff < 0 -> Grid.Direction.SE
            rowDiff < 0 && colDiff > 0 -> Grid.Direction.SW
            else -> throw RuntimeException("$this and $other are the same point, there's no direction")
        }
    }

    fun getPairAbsDistance(other: GridCoord) : Pair<Int, Int> {
        return abs(this.row - other.row) to abs(this.col - other.col)
    }

    fun move(direction: Grid.Direction, unitRow: Int, unitCol: Int) : GridCoord {
        return when (direction) {
            Grid.Direction.N -> GridCoord(row - unitRow, col)
            Grid.Direction.S -> GridCoord(row + unitRow, col)
            Grid.Direction.E -> GridCoord(row, col + unitCol)
            Grid.Direction.W -> GridCoord(row , col - unitCol)
            Grid.Direction.NE -> move(Grid.Direction.N, unitRow, 0).move(Grid.Direction.E, 0, unitCol)
            Grid.Direction.NW -> move(Grid.Direction.N, unitRow, 0).move(Grid.Direction.W, 0, unitCol)
            Grid.Direction.SE -> move(Grid.Direction.S, unitRow, 0).move(Grid.Direction.E, 0, unitCol)
            Grid.Direction.SW -> move(Grid.Direction.S, unitRow, 0).move(Grid.Direction.W, 0, unitCol)
        }
    }

    fun isNextTo(other: GridCoord) : Boolean {
        return abs(this.row - other.row) == 0 && abs(this.col - other.col) == 1 ||
                abs(this.row - other.row) == 1 && abs(this.col - other.col) == 0
    }

}

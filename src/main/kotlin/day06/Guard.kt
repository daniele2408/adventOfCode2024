package org.example.day06

import arrow.core.left
import arrow.core.right
import org.example.model.Grid
import org.example.model.GridCoord
import org.example.model.GridCoordValue

class Guard(val gridCoord: GridCoord, val direction: Grid.Direction) {

    companion object {
        fun initGuard(inputRows: List<String>) : Guard {
            val (row, col, _) = inputRows
                .flatMapIndexed { idxRow, row ->
                    row.mapIndexed { idxCol, cell ->
                        Triple(idxRow, idxCol, cell)
                    }
                }.find { it.third == '^' }!!
            return Guard(GridCoord(row, col), direction = Grid.Direction.N)
        }
    }

    fun turnRight(newGc: GridCoord) : Guard {
        return Guard(newGc, lookRight())
    }

    fun lookRight() : Grid.Direction {
        return when (direction) {
            Grid.Direction.N -> Grid.Direction.E
            Grid.Direction.E -> Grid.Direction.S
            Grid.Direction.S -> Grid.Direction.W
            Grid.Direction.W -> Grid.Direction.N
            else -> throw RuntimeException("Direction $direction not supported")
        }
    }
}
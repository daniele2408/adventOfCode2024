package org.example.day06

import org.example.model.GridCoord
import org.example.model.GridCoordValue
import org.example.model.GridSparse

fun createGridSparseStuff(inputRows: List<String>) : GridSparse<Stuff> {
    val data = inputRows
        .flatMapIndexed { idxRow, row ->
            row.mapIndexed { idxCol, cell ->
                if (cell == '#') GridCoordValue(idxRow, idxCol, Stuff.from(cell.toString()))
                else null
            }.filterNotNull()
        }.toSet()
    return GridSparse(data)
}

fun GridSparse<Stuff>.patrol(guard: Guard) : Guard? {
    val nextCell = this.peekNext(guard.gridCoord, guard.direction)
    if (nextCell.let { this.isOutside(it) }) return null
    if (this.get(nextCell)?.let { it.value == Obstacle } == true) {
        val nextRightCell = this.peekNext(guard.gridCoord, guard.lookRight())
        if (nextRightCell.let { this.isOutside(it) }) return null
        return guard.turnRight(nextRightCell)
    }
    return Guard(nextCell, guard.direction)
}
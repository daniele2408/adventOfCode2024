package org.example.day06

import org.example.model.Grid
import org.example.model.GridCoord
import org.example.model.GridSparse

class PatrolTracker(val grid: GridSparse<Stuff>, guard: Guard) {
    var patrolPositions: MutableSet<GridCoord> = mutableSetOf(guard.gridCoord)
    var guard: Guard? = guard

    fun track() : Int {
        move()
        return patrolPositions.size
    }

    fun move() {
        do {
            guard = grid.patrol(guard!!)
            if (guard != null) patrolPositions.add(guard!!.gridCoord)
        } while (guard != null)
    }

}
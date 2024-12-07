package org.example.day06

import org.example.logic.butLast
import org.example.model.Grid
import org.example.model.GridCoord
import org.example.model.GridDense

class PatrolTrackerSimulator(val grid: GridDense<Stuff>, guard: Guard) {

    var patrolPositions: MutableMap<GridCoord, MutableMap<Grid.Direction, Int>> = mutableMapOf(guard.gridCoord to mutableMapOf(guard.direction to 1))
    val patrolPath: MutableList<Pair<GridCoord, Grid.Direction>> = mutableListOf()
    var guard: Guard? = guard

    fun trackPos() : Int {
        return patrolPath.map { it.first }.distinct().size
    }

    fun move() {
        do {
            val nextGuard = grid.patrol(guard!!)
            patrolPath += grid.sliceSectionUntilObstacleFrom(guard!!.gridCoord, guard!!.direction).map { it.justCoord() to guard!!.direction }
            if (nextGuard != null) {
                addToCounter(nextGuard.gridCoord, nextGuard.direction)
            }
            guard = nextGuard
        } while (guard != null)
    }
    fun moveAndCheckLoop() : Boolean {
        do {
            val nextGuard = grid.patrol(guard!!)
            val pathToAdd = grid.sliceSectionUntilObstacleFrom(guard!!.gridCoord, guard!!.direction).butLast()
                .map { it.justCoord() to guard!!.direction }
            patrolPath += pathToAdd
            if (nextGuard != null) {
                addToCounter(nextGuard.gridCoord, nextGuard.direction)
            }
            guard = nextGuard
        } while (!isLooping() && guard != null)


        return isLooping()
    }


    private fun isLooping() : Boolean {
        val count = patrolPositions.entries.count { it.value.values.sum() > 100 }
        return count == 2
    }

    private fun addToCounter(gridCoord: GridCoord, direction: Grid.Direction) {
        if (gridCoord in patrolPositions) {
            val entry = patrolPositions[gridCoord]!!
            if (direction in entry) {
                patrolPositions[gridCoord]!![direction] = patrolPositions[gridCoord]!![direction]!! + 1
            }
            else patrolPositions[gridCoord]!![direction] = 1
        } else {
            patrolPositions[gridCoord] = mutableMapOf(direction to 1)
        }
    }

}
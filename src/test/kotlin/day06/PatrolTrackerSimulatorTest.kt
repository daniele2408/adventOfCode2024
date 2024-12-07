package day06

import org.example.day06.*
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.structure.getInputRows

class PatrolTrackerSimulatorTest {
    @Test
    fun testPatrol() {
        val inputRows = getInputRows("day06/testA.txt")
        val grid = createGridDenseStuff(inputRows)
        val guard = Guard.initGuard(inputRows)
        val patrolTracker = PatrolTrackerSimulator(
            grid,
            guard
        )
        patrolTracker.move()

        assertEquals(5, patrolTracker.trackPos())
    }

    @Test
    fun testPatrolNoLoop() {
        val inputRows = getInputRows("day06/testA.txt")
        val res = howManyDifferentPositionsToEnforceLoop(inputRows)

        assertEquals(0, res)
    }

    @Test
    fun testPatrolLoop() {
        val inputRows = getInputRows("day06/testB.txt")
        val res = howManyDifferentPositionsToEnforceLoop(inputRows)

        assertEquals(1, res)

    }

}
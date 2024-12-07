package org.example.day06

fun howManyDistinctPosition(inputRows: List<String>) : Int {
    val patrolTracker = PatrolTrackerSimulator(
        createGridDenseStuff(inputRows),
        Guard.initGuard(inputRows)
    )
    patrolTracker.move()
    return patrolTracker.trackPos()
}

fun howManyDifferentPositionsToEnforceLoop(inputRows: List<String>) : Int {
    val grid = createGridDenseStuff(inputRows)
    val guard = Guard.initGuard(inputRows)
    val patrolTracker = PatrolTrackerSimulator(
        grid,
        guard
    )
    patrolTracker.move()
    val path = patrolTracker.patrolPath

    val res = path
        .asSequence()
        .zipWithNext()
        .filter { (a, b) -> a.first != b.first && b.first != guard.gridCoord }
        .filter { (currentStep, nextStep) ->
            val gridSimulation = grid.addObstacleToGrid(nextStep.first)
            val simulation = PatrolTrackerSimulator(
                gridSimulation,
                guard
            )
            simulation.moveAndCheckLoop()
    }.map { it.second.first }.distinct().count()
    return res
}
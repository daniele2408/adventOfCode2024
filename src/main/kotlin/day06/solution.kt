package org.example.day06

fun howManyDistinctPosition(inputRows: List<String>) : Int {
    val grid = createGridSparseStuff(inputRows)
    val patrolTracker = PatrolTracker(
        grid,
        Guard.initGuard(inputRows)
    )
    return patrolTracker.track()
}
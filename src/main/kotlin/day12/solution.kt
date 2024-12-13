package org.example.day12

import org.example.logic.permutationOneAndRest
import org.example.model.Grid
import org.example.model.GridCoordValue
import org.example.model.GridDense

fun totalFencingPrice(inputRows: List<String>) : Int {
    val grid: GridDense<Char> = GridDense.fromInput(
        inputRows.map { it.map { c -> c } }
    )

    val distinctValues = grid.dataAsSeq().map { it.value }.distinct().toSet()
    val areas: Set<Area> = distinctValues.flatMap {
        grid.collectAreasForChar(it, setOf())
    }.toSet()

    return areas.sumOf { it.size * it.computerPerimeter() }
}

fun totalFencingPriceTheSideWay(inputRows: List<String>) : Int {
    val grid: GridDense<Char> = GridDense.fromInput(
        inputRows.map { it.map { c -> c } }
    )

    val distinctValues = grid.dataAsSeq().map { it.value }.distinct().toSet()
    val areas: Set<Area> = distinctValues.flatMap {
        grid.collectAreasForChar(it, setOf())
    }.toSet()

    return areas.sumOf { it.size * grid.countCorners(it) }
}

typealias Area = Set<GridCoordValue<Char>>

fun Area.containsGc(gridCoordValue: GridCoordValue<Char>) : Boolean {
    return gridCoordValue in this
}

fun Area.computerPerimeter() : Int {
    return this.permutationOneAndRest().sumOf {
        (e, rest) -> 4 - rest.count { e.justCoord().isNextTo(it.justCoord()) }
    }
}

fun GridDense<Char>.collectAreasForChar(c: Char, areas: Set<Area>) : Set<Area> {
    val charSample: GridCoordValue<Char>? = this.dataAsSeq().find { it.value == c && areas.none { area -> area.containsGc(it) } }
    if (charSample == null) return areas
    val charArea = this.spanArea(ArrayDeque(listOf(charSample)), setOf())
    return this.collectAreasForChar(c, areas + setOf(charArea))
}

fun GridDense<Char>.spanArea(frontier: ArrayDeque<GridCoordValue<Char>>, acc: Area) : Area {
    if (frontier.isEmpty()) return acc
    val gcv = frontier.removeFirst()
    val aroundArea: Area = getNSWEAround(gcv).filter { it.value == gcv.value && !acc.containsGc(it) && it !in frontier }
        .toSet()
    frontier.addAll(aroundArea)
    return this.spanArea(frontier, acc + setOf(gcv))
}

fun GridDense<Char>.countCorners(area: Area): Int {
    if (area.isEmpty()) return 0
    if (area.size == 1) return 4
    return area.sumOf {
        Corner.entries.count { c -> c.test.invoke(this, it) }
    }
}

fun GridDense<Char>.isDifferentAt(center: GridCoordValue<Char>, dir: Grid.Direction) : Boolean {
    val next = getNext(center, dir)
    return if (next == null) true
    else next.value != center.value
}

fun GridDense<Char>.isDifferentAt(center: GridCoordValue<Char>, dirs: Set<Grid.Direction>) : Boolean {
    return dirs.all { isDifferentAt(center, it) }
}

fun GridDense<Char>.isSameAt(center: GridCoordValue<Char>, dirs: Set<Grid.Direction>) : Boolean {
    return dirs.none { isDifferentAt(center, it) }
}

enum class Corner(val test: (GridDense<Char>, GridCoordValue<Char>) -> Boolean) {
    UPPER_LEFT( { grid, center -> grid.isDifferentAt(center, setOf(Grid.Direction.N, Grid.Direction.W))  } ),
    UPPER_RIGHT( { grid, center -> grid.isDifferentAt(center, setOf(Grid.Direction.N, Grid.Direction.E)) } ),
    LOWER_LEFT( { grid, center -> grid.isDifferentAt(center, setOf(Grid.Direction.S, Grid.Direction.W)) } ),
    LOWER_RIGHT( { grid, center -> grid.isDifferentAt(center, setOf(Grid.Direction.S, Grid.Direction.E)) } ),

    INTERNAL_UPPER_LEFT( { grid, center -> grid.isDifferentAt(center, setOf(Grid.Direction.SE)) && grid.isSameAt(center, setOf(Grid.Direction.S, Grid.Direction.E)) } ),
    INTERNAL_UPPER_RIGHT( { grid, center -> grid.isDifferentAt(center, setOf(Grid.Direction.SW)) && grid.isSameAt(center, setOf(Grid.Direction.S, Grid.Direction.W)) } ),
    INTERNAL_LOWER_LEFT( { grid, center -> grid.isDifferentAt(center, setOf(Grid.Direction.NE)) && grid.isSameAt(center, setOf(Grid.Direction.N, Grid.Direction.E)) } ),
    INTERNAL_LOWER_RIGHT( { grid, center -> grid.isDifferentAt(center, setOf(Grid.Direction.NW)) && grid.isSameAt(center, setOf(Grid.Direction.N, Grid.Direction.W)) } )
}
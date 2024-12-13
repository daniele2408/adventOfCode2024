package org.example.day12

import org.example.logic.permutationOneAndRest
import org.example.model.GridCoord
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

//    areas.forEach {
//        println("Area [${it.first().value}] size [${it.size}] perimeter [${it.computerPerimeter()}]")
//    }

    return areas.sumOf { it.size * it.computerPerimeter() }
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
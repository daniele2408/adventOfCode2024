package org.example.day08

import org.example.logic.permutationOneAndRest
import org.example.model.Grid
import org.example.model.GridCoord
import org.example.model.GridCoordValue
import org.example.model.GridSparse

fun howManyAntinodes(inputRows: List<String>) : Int {
    val grid = GridSparse.fromInputTransforming(
        inputRows.map { row -> row.toList() }, { c -> Antenna(c) }, { a -> a.symbol != '.' }
    )
    return grid.computeAllAntinodesLocation()
}

fun howManyAntinodesWithResonance(inputRows: List<String>) : Int {
    val grid = GridSparse.fromInputTransforming(
        inputRows.map { row -> row.toList() }, { c -> Antenna(c) }, { a -> a.symbol != '.' }
    )
    return grid.computeAllAntinodesLocation(true)
}

fun GridSparse<Antenna>.computeAllAntinodesLocation(accountForResonance: Boolean = false) : Int {
    val antennaTypes = dataAsSeq().map { it.value }.distinct()
    val antinodes = antennaTypes.flatMap { computeAntinodesForAntenna(it, accountForResonance) }.toSet()
    val draw = this.addData(antinodes.map { GridCoordValue.from(it, Antenna('#')) }.toSet())
        .draw(
            { a -> a.symbol.toString() }
        )
    println(draw)
    return antinodes.size
}

fun GridSparse<Antenna>.computeAntinodesForAntenna(antenna: Antenna, accountForResonance: Boolean) : Set<GridCoord> {
    val antennaSameType = data.filter { it.value == antenna }.toList()
    return antennaSameType.permutationOneAndRest().flatMap { (a, rest) ->
        rest.flatMap { other ->
            val dir = a.getDirectionTo(other)
            val (rowDistance, colDistance) = a.getPairAbsDistance(other)
            if (accountForResonance) computeAntinodeResonancePosition(a.justCoord(), dir, rowDistance, colDistance)
            else setOf(computeAntinodePosition(a.justCoord(), dir, rowDistance, colDistance)).filterNotNull()
        }
    }.toSet()
}

fun GridSparse<Antenna>.computeAntinodePosition(
    startPoint: GridCoord,
    direction: Grid.Direction,
    rowDistance: Int,
    colDistance: Int
): GridCoord? {
    val antinodeLocation = startPoint.move(direction, rowDistance * 2, colDistance * 2)
    return if (isOutside(antinodeLocation)) null
    else antinodeLocation
}

fun GridSparse<Antenna>.computeAntinodeResonancePosition(
    startPoint: GridCoord,
    direction: Grid.Direction,
    rowDistance: Int,
    colDistance: Int
): Set<GridCoord> {

    return generateSequence(1) { it + 1 }.map {
        startPoint.move(direction, rowDistance * it, colDistance * it)
    }.takeWhile { !isOutside(it) }.toSet()

}
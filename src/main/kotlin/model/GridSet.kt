package org.example.model

import arrow.core.memoize
import org.example.day04.scanDirection
import kotlin.math.abs

class GridSet<T>(val data: Set<GridCoordValue<T>>) {
    val nRow = data.maxBy { it.row }.row
    val nCol = data.maxBy { it.col }.col
    companion object {

        fun <T> fromInput(data: List<List<T>>) : GridSet<T> {
            return GridSet(
                data.flatMapIndexed { idxRow, row -> row.mapIndexed { idxCol, cell -> GridCoordValue<T>(idxRow, idxCol, cell) } }.toSet()
            )
        }

    }

    fun reverseGridCoordHorizontallyAsimmetrically(foldRow : Int, newMaxRow : Int, p: GridCoordValue<T>) : GridCoordValue<T> {
        return GridCoordValue(newMaxRow - abs(p.row - foldRow), p.col, p.value)
    }

    fun reverseGridCoordVerticallyAsimmetrically(foldCol : Int, newMaxCol : Int, p: GridCoordValue<T>) : GridCoordValue<T> {
        return GridCoordValue(p.row, newMaxCol - abs(p.col - foldCol), p.value)
    }

    fun printMap(printFunc: (T) -> String) : String {
        return (0..nRow).map { idxRow -> (0..nCol).map { idxCol -> this.get(idxCol, idxRow)?.value?.let { printFunc.invoke(it) } }.joinToString("") }.joinToString("\n")
    }

    fun applyConsumer(gridCoordValue: GridCoordValue<T>, consumer: (T) -> Unit) {
        get(gridCoordValue)?.let { consumer.invoke(it.value) }
    }

    fun get(col: Int, row: Int) : GridCoordValue<T>? {
        return data.find { it.col == col && it.row == row }
    }

    fun get(gridCoordValue: GridCoordValue<T>) : GridCoordValue<T>? {
        return get(gridCoordValue.col, gridCoordValue.row)
    }

    fun getNext(gc: GridCoordValue<T>, direction: Direction) : GridCoordValue<T>?{
        return when (direction) {
            Direction.S -> get(gc.col, gc.row+1)
            Direction.N -> get(gc.col, gc.row-1)
            Direction.E -> get(gc.col+1, gc.row)
            Direction.W -> get(gc.col-1, gc.row)
            Direction.NE -> getNext(gc, Direction.N)?.let { getNext(it, Direction.E)}
            Direction.NW -> getNext(gc, Direction.N)?.let { getNext(it, Direction.W)}
            Direction.SE -> getNext(gc, Direction.S)?.let { getNext(it, Direction.E)}
            Direction.SW -> getNext(gc, Direction.S)?.let { getNext(it, Direction.W)}
        }
    }

    private fun getNextEightDirections(gc: GridCoordValue<T>, directions: List<Direction>) : GridCoordValue<T>?{
        if (directions.isEmpty()) return gc
        return getNext(gc, directions.first())?.let { getNextEightDirections(it, directions.subList(1, directions.size)) }
    }

    fun getAllAdjacents(gc: GridCoordValue<T>) : List<GridCoordValue<T>> {
        return Direction.entries.map { getNext(gc, it) }.filterNotNull().toList()
    }

    fun getAllAdjacentsEightDirections(gc: GridCoordValue<T>) : List<GridCoordValue<T>> {
        return (Direction.entries.map { listOf(it) } + listOf(
            listOf(Direction.W, Direction.N),
            listOf(Direction.W, Direction.S),
            listOf(Direction.E, Direction.N),
            listOf(Direction.E, Direction.S),
        )).map { getNextEightDirections(gc, it) }.filterNotNull().toList()
    }

    fun getAllAdjacentsEightDirectionsWhere(gc: GridCoordValue<T>, predicate: (GridCoordValue<T>) -> Boolean) : Set<GridCoordValue<T>> {
        return this.getAllAdjacentsEightDirections(gc).filter { predicate.invoke(it) }.toSet()
    }

    enum class Direction {
        N, S, E, W,
        NE, NW, SE, SW;
    }

}
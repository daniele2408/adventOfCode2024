package org.example.model

interface Grid<T> {

    fun dataAsSeq() : Sequence<GridCoordValue<T>>

    fun get(col: Int, row: Int) : GridCoordValue<T>?

    fun get(gridCoordValue: GridCoordValue<T>) : GridCoordValue<T>?

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

    enum class Direction {
        N, S, E, W,
        NE, NW, SE, SW;
    }
}
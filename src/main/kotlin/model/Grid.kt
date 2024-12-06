package org.example.model

interface Grid<T> {

    fun dataAsSeq() : Sequence<GridCoordValue<T>>

    fun get(col: Int, row: Int) : GridCoordValue<T>?

    fun get(gridCoordValue: GridCoordValue<T>) : GridCoordValue<T>?

    fun get(coord: GridCoord) : GridCoordValue<T>? {
        return get(coord.col, coord.row)
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

    fun peekNext(gc: GridCoord, direction: Direction) : GridCoord {
        return when (direction) {
            Direction.S -> GridCoord(gc.row+1, gc.col)
            Direction.N -> GridCoord(gc.row-1, gc.col)
            Direction.E -> GridCoord(gc.row, gc.col+1)
            Direction.W -> GridCoord(gc.row, gc.col-1)
            Direction.NE -> peekNext(peekNext(gc, Direction.N), Direction.E)
            Direction.NW -> peekNext(peekNext(gc, Direction.N), Direction.W)
            Direction.SE -> peekNext(peekNext(gc, Direction.S), Direction.E)
            Direction.SW -> peekNext(peekNext(gc, Direction.S), Direction.W)
        }
    }

    enum class Direction {
        N, S, E, W,
        NE, NW, SE, SW;
    }
}
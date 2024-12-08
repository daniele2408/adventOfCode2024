package org.example.model


data class GridCoordValue<T>(val row: Int, val col: Int, val value: T) {

    fun justCoord() : GridCoord {
        return GridCoord(row, col)
    }

    fun getDirectionTo(other: GridCoordValue<T>) : Grid.Direction {
        return this.justCoord().getDirectionTo(other.justCoord())
    }

    fun getPairAbsDistance(other: GridCoordValue<T>) : Pair<Int, Int> {
        return this.justCoord().getPairAbsDistance(other.justCoord())
    }

    companion object {
        fun <T> from(gridCoord: GridCoord, value: T) : GridCoordValue<T> {
            return GridCoordValue(gridCoord.row, gridCoord.col, value)
        }
    }
}
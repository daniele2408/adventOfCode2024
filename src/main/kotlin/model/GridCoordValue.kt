package org.example.model


data class GridCoordValue<T>(val row: Int, val col: Int, val value: T) {
    fun justCoord() : GridCoord {
        return GridCoord(row, col)
    }
    companion object {
        fun <T> from(gridCoord: GridCoord, value: T) : GridCoordValue<T> {
            return GridCoordValue(gridCoord.row, gridCoord.col, value)
        }
    }
}
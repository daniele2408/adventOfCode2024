package org.example.model


data class GridCoordValue<T>(val row: Int, val col: Int, val value: T) {
    fun justCoord() : GridCoord {
        return GridCoord(row, col)
    }
}
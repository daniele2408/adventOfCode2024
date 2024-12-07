package org.example.model

class GridDense<T>(val data: List<List<GridCoordValue<T>>>) : Grid<T> {
    val nRow = data.size
    val nCol = data.first().size

    companion object {

        fun <T> fromInput(data: List<List<T>>) : GridDense<T> {
            return GridDense(
                data.mapIndexed { idxRow, row -> row.mapIndexed { idxCol, el -> GridCoordValue(idxRow, idxCol, el) } }
            )
        }

    }

    fun selectCol(idxCol: Int) : List<GridCoordValue<T>> {
        return data.map { it[idxCol] }
    }

    fun selectRow(idxRow: Int) : List<GridCoordValue<T>> {
        return data[idxRow]
    }

    fun sliceCol(startIdxRow: Int, endIdxRow: Int, idxCol: Int) : List<GridCoordValue<T>> {
        require(startIdxRow <= endIdxRow)
        return selectCol(idxCol).filter { it.row in (startIdxRow..endIdxRow) }
    }

    fun sliceRow(startIdxCol: Int, endIdxCol: Int, idxRow: Int) : List<GridCoordValue<T>> {
        require(startIdxCol <= endIdxCol)
        return selectRow(idxRow).filter { it.col in (startIdxCol..endIdxCol) }
    }


    override fun dataAsSeq() : Sequence<GridCoordValue<T>> {
        return data.flatten().asSequence()
    }


    override fun get(col: Int, row: Int) : GridCoordValue<T>? {
        if (col !in (0..(nCol-1)) || row !in (0..(nRow-1))) return null
        return data[row][col]
    }

    override fun get(gridCoordValue: GridCoordValue<T>) : GridCoordValue<T>? {
        return get(gridCoordValue.col, gridCoordValue.row)
    }

    fun isOutside(gridCoord: GridCoord) : Boolean {
        return gridCoord.col !in (0..nCol) || gridCoord.row !in (0..nRow)
    }

}
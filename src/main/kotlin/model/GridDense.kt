package org.example.model

class GridDense<T>(private val data: List<List<GridCoordValue<T>>>) : Grid<T> {
    val nRow = data.size
    val nCol = data.first().size

    companion object {

        fun <T> fromInput(data: List<List<T>>) : GridDense<T> {
            return GridDense(
                data.mapIndexed { idxRow, row -> row.mapIndexed { idxCol, el -> GridCoordValue(idxRow, idxCol, el) } }
            )
        }

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



}
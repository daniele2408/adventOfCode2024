package org.example.model

class GridSparse<T>(val data: Set<GridCoordValue<T>>) : Grid<T> {
    val nRow = data.maxBy { it.row }.row
    val nCol = data.maxBy { it.col }.col

    companion object {

        fun <T> fromInput(data: List<List<T>>) : GridSparse<T> {
            return GridSparse(
                data.flatMapIndexed { idxRow, row -> row.mapIndexed { idxCol, cell -> GridCoordValue<T>(idxRow, idxCol, cell) } }.toSet()
            )
        }

    }

    override fun dataAsSeq() : Sequence<GridCoordValue<T>> {
        return data.asSequence()
    }

    override fun get(col: Int, row: Int) : GridCoordValue<T>? {
        return data.find { it.col == col && it.row == row }
    }

    override fun get(gridCoordValue: GridCoordValue<T>) : GridCoordValue<T>? {
        return get(gridCoordValue.col, gridCoordValue.row)
    }

    fun isOutside(gridCoord: GridCoord) : Boolean {
        return gridCoord.col !in (0..nCol) || gridCoord.row !in (0..nRow)
    }


}
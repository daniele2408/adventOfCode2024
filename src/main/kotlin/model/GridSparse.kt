package org.example.model

import javax.xml.crypto.Data

class GridSparse<T>(val data: Set<GridCoordValue<T>>, private val nRow: Int, private val nCol: Int) : Grid<T> {

    constructor(data: Set<GridCoordValue<T>>) : this(data, data.maxBy { it.row }.row, data.maxBy { it.col }.col)

    companion object {

        fun <T> fromInput(data: List<List<T>>) : GridSparse<T> {
            return GridSparse(
                data.flatMapIndexed { idxRow, row -> row.mapIndexed { idxCol, cell -> GridCoordValue<T>(idxRow, idxCol, cell) } }.toSet()
            )
        }

        fun <T,U> fromInputTransforming(data: List<List<U>>, func: (U) -> T, pred: (T) -> Boolean) : GridSparse<T> {
            return GridSparse(
                data.flatMapIndexed { idxRow, row -> row.mapIndexed { idxCol, cell -> GridCoordValue<T>(idxRow, idxCol, func(cell)) } }
                    .filter { pred.invoke(it.value) }
                    .toSet(),
                data.size-1,
                data.first().size-1
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

    fun addData(newData: Set<GridCoordValue<T>>) : GridSparse<T> {
        return GridSparse (
            data + newData
        )
    }

    fun draw(drawFunc: (T) -> String, emptySpaceRepr: String = ".") : String {
        return (0..nRow).joinToString("\n") {idxRow ->
            (0..nCol).joinToString("") { idxCol ->
                val cell = get(idxCol, idxRow)
                if (cell == null) emptySpaceRepr
                else drawFunc(cell.value)
            }
        }
    }


}
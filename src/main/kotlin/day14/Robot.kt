package org.example.day14

import org.example.model.GridSparse
import kotlin.math.abs

data class Robot(val startX: Int, val startY: Int, val vX: Int, val vY: Int) {
    var posX = startX
    var posY = startY
    companion object {
        fun from(input: String) : Robot {
            val (startPos, velocity) = input.split(" ")
            val (x, y) = startPos.split("=").last().split(",").map { it.toInt() }
            val (vX, vY) = velocity.split("=").last().split(",").map { it.toInt() }
            return Robot(x, y, vX, vY)
        }
    }

    fun <T> moveOn(grid : GridSparse<T>) {
        val nextX = posX + vX
        val nextY = posY + vY

        posX = if (nextX >= 0) nextX.rem(grid.nCol) else grid.nCol - abs(nextX)
        posY = if (nextY >= 0) nextY.rem(grid.nRow) else grid.nRow - abs(nextY)
    }
}
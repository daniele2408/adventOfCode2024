package org.example.day14

import org.example.model.GridCoordValue
import org.example.model.GridSparse
import org.structure.getResourceAsText
import java.io.File
import java.math.BigInteger
import kotlin.math.max
import kotlin.math.pow

fun computeSecurityFactor(inputRows: List<String>) : BigInteger {
    val robots = inputRows.map { Robot.from(it) }

    val isSample = inputRows.size == 12
    val (nRow, nCol) = if (isSample) (7 to 11) else (103 to 101) // different input from sample, come on :(
    val grid = GridSparse(
        robots.map { GridCoordValue(it.posY, it.posX, it) }.toSet(), nRow, nCol
    )

    robots.map { robot ->
        repeat(100) {
            robot.moveOn(grid)
        }
    }

    return computeSafetyFactor(grid, robots)
}

fun computeSecurityFactorEasterEggLowestTotSteps(inputRows: List<String>) : Int {

    val isSample = inputRows.size == 12
    if (isSample) return -1

    val (nRow, nCol) = 103 to 101

    val robots = inputRows.map { Robot.from(it) }
    val grid = GridSparse<Robot>(
        setOf(), nRow, nCol
    )
    val mapLoopVarColRowPos: Map<Int, Pair<Double, Double>> = (1..max(nRow,nCol)).associateWith {
        robots.map { it.moveOn(grid) }
        robots.map { it.posX }.variance() to robots.map { it.posY }.variance()
    }

    val minX = mapLoopVarColRowPos.entries.minBy { it.value.first }.key
    val minY = mapLoopVarColRowPos.entries.minBy { it.value.second }.key

    // shout-out to https://www.reddit.com/r/adventofcode/comments/1he0asr/2024_day_14_part_2_why_have_fun_with_image/
    val iterationCongruentLeastVariance = computeCongruent(minY, nCol, nRow, minX)

    // finally complete until that loop and print result
    (max(nRow,nCol)+1 ..  iterationCongruentLeastVariance).forEach { _ ->
        robots.map { it.moveOn(grid) }
    }
    println(drawRobots(robots, nRow, nCol))

    return iterationCongruentLeastVariance
}

private fun drawRobots(
    robots: List<Robot>,
    nRow: Int,
    nCol: Int
) = GridSparse(
    robots.map { GridCoordValue(it.posY, it.posX, it) }.toSet(), nRow, nCol
).draw()

private fun computeCongruent(minY: Int, nCol: Int, nRow: Int, minX: Int) =
    minY + (BigInteger.valueOf(nCol.toLong()).modInverse(BigInteger.valueOf(nRow.toLong()))
        .toInt() * (minX - minY) % nCol) * nRow

fun List<Int>.variance() : Double {
    val mean = this.sum().toDouble() / this.size
    return this.sumOf { (it - mean).pow(2) } / size
}

fun GridSparse<Robot>.draw(emptySpaceRepr: String = ".") : String {
    return (0..<nRow).joinToString("\n") {idxRow ->
        (0..<nCol).joinToString("") { idxCol ->
            val cell = getAll(idxCol, idxRow).count()
            if (cell == 0) emptySpaceRepr
            else cell.toString()
        }
    }
}

fun <T> computeSafetyFactor(grid : GridSparse<T>, robots: Collection<Robot>) : BigInteger {
    val totQ1 = robots.count { it.posY < (grid.nRow - 1) / 2  && it.posX < (grid.nCol - 1) / 2}
    val totQ2 = robots.count { it.posY < (grid.nRow - 1) / 2  && it.posX > (grid.nCol - 1) / 2}
    val totQ3 = robots.count { it.posY > (grid.nRow - 1) / 2  && it.posX < (grid.nCol - 1) / 2}
    val totQ4 = robots.count { it.posY > (grid.nRow - 1) / 2  && it.posX > (grid.nCol - 1) / 2}

    return totQ1.toBigInteger() * totQ2.toBigInteger() * totQ3.toBigInteger() * totQ4.toBigInteger()
}
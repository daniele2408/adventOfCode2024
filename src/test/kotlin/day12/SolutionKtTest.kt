package day12

import org.example.day12.totalFencingPriceTheSideWay
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.structure.getInputRows

class SolutionKtTest {
    @Test
    fun countCorners() {
        val inputRows = getInputRows("day12/testA.txt")

        val res = totalFencingPriceTheSideWay(inputRows)

        assertEquals(17 * 12 + 4 * 4 + 4 * 4, res)
    }
}
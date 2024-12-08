package day08

import org.example.day08.howManyAntinodes
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.structure.getInputRows

class SolutionKtTest {
    @Test
    fun testA() {
        val inputRows = getInputRows("day08/testA.txt")
        val res = howManyAntinodes(inputRows)
        assertEquals(2, res)
    }

    @Test
    fun testB() {
        val inputRows = getInputRows("day08/testB.txt")
        val res = howManyAntinodes(inputRows)
        assertEquals(4, res)
    }

    @Test
    fun testC() {
        val inputRows = getInputRows("day08/testC.txt")
        val res = howManyAntinodes(inputRows)
        assertEquals(4, res)
    }
}
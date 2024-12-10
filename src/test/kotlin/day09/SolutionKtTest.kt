package day09

import org.example.day09.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import kotlin.test.fail

class SolutionKtTest {

    @Test
    fun test() {
        val s = "2333133121414131402"
        val res = turnDiskMapToBlock(s)
        assertEquals("00...111...2...333.44.5555.6666.777.888899".map { if (it == '.') -1 else it.toString().toInt() }.toList(), res)

    }

    @Test
    fun test2() {
        val s = "2333133121414131402"
        val block = turnDiskMapToBlock(s)
        val res = rearrangeBlock(block)
        assertEquals("0099811188827773336446555566".map { it.toString().toInt() }.toList(), res)
    }

    @Test
    fun testBlock() {
        val s = "2333133121414131402"
        val block = turnDiskMapToArrayBlock(s)
        val res = rearrangeBlockWhole(block)

        assertEquals(2858.toBigInteger(), computeChecksumBlock(res))
    }

    @Test
    fun testBlock2() {
        // 00111222....3333
        val res = rearrangeBlockWhole(
            arrayOf(Block(0, 2), Block(1, 3), Block(2, 3), Block(-1, 4), Block(3, 4))
        )

        val expected = arrayOf(Block(0, 2), Block(1, 3), Block(2, 3), Block(3, 4), Block(-1, 4))
        assertTrue((expected.zip(res).all { p -> p.first == p.second }))
    }

    @Test
    fun testBlock3() {
        // 00111222...3333
        val input = arrayOf(Block(0, 2), Block(1, 3), Block(2, 3), Block(-1, 3), Block(3, 4))
        val res = rearrangeBlockWhole(
            input
        )

        assertTrue((input.zip(res).all { p -> p.first == p.second }))
    }

    @Test
    fun testBlock4() {
        // 00111222....333 -> 00111222333....
        val input = arrayOf(Block(0, 2), Block(1, 3), Block(2, 3), Block(-1, 4), Block(3, 3))
        val res = rearrangeBlockWhole(
            input
        )

        val expected = arrayOf(Block(0, 2), Block(1, 3), Block(2, 3), Block(3, 3), Block(-1, 4))
        assertTrue((expected.zip(res).all { p -> p.first == p.second }))
    }

    @Test
    fun testBlock5() {
        // 0...1...2......33333 -> 021......33333......
        val input = arrayOf(Block(0, 1), Block(-1, 3), Block(1, 1), Block(-1, 3), Block(2, 1),
            Block(-1, 6), Block(3, 5))
        val res = rearrangeBlockWhole(
            input
        )

        val expected = arrayOf(Block(0, 1), Block(2, 1), Block(1, 1), Block(-1, 6), Block(3, 5), Block(-1, 6))
        assertTrue((expected.zip(res).all { p -> p.first == p.second }))
    }

}
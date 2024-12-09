package day09

import org.example.day09.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

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

}
package day03

import org.example.day03.addingUpParsedMultiplication
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import kotlin.test.fail

class SolutionKtTest {
    @Test
    fun testIt() {
        val res = addingUpParsedMultiplication(
            listOf("vdskpfewmul(123,334)mul(412,321)4fw")
        )

        assertEquals(173334, res)
    }

    @Test
    fun testIterationIndexed() {
        val res = addingUpParsedMultiplication(
            listOf(
                "xmul(2,4)%&mul[3,7]!@^do_not_mul(5,5)+mul(32,64]then(mul(11,8)mul(8,5))",
                "xmul(2,4)%&mul[3,7]!@^do_not_mul(5,5)+mul(32,64]then(mul(11,8)mul(8,5))"
            )
        )

        assertEquals(161 * 2, res)
    }
}
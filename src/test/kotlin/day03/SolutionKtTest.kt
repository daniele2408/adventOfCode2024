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
}
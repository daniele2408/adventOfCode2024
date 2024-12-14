package day13

import org.example.day13.clawMachinesCreate
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.structure.getInputRows

class ClawMachineTest {
    @Test
    fun test() {
        val clawMachine = clawMachinesCreate(getInputRows("day13/testA.txt")).first()

        val (pushA, pushB) = clawMachine.solve()!!
        assertEquals(80, pushA)
        assertEquals(40, pushB)

    }

    @Test
    fun test2() {
        val clawMachine = clawMachinesCreate(getInputRows("day13/testB.txt")).first()

        val resNull = clawMachine.solve() ?: null
        assertNull(resNull)
    }
}
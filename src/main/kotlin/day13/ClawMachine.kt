package org.example.day13

class ClawMachine(val a: Button, val b: Button, val prize: Prize) {
    companion object {
        fun from(inputs: List<String>, startOff: Long = 0) : ClawMachine {
            val buttonAInput = inputs.first { it.startsWith("Button A:") }
            val buttonBInput = inputs.first { it.startsWith("Button B:") }
            val prizeInput = inputs.first { it.startsWith("Prize:") }

            return ClawMachine(Button.from(buttonAInput), Button.from(buttonBInput), Prize.from(prizeInput, startOff))
        }
    }

    fun Pair<Long, Long>.isSolution() : Boolean {
        return this.first * a.moveX + this.second * b.moveX == prize.x &&
                this.first * a.moveY + this.second * b.moveY == prize.y
    }

    fun solve() : Pair<Long, Long>? {
        val num = (prize.x * b.moveY) - (prize.y * b.moveX)
        val den = (a.moveX * b.moveY - a.moveY * b.moveX)
        val timesPushA = num / den
        val timesPushB = (prize.y - timesPushA * a.moveY) / b.moveY

        val res = Pair(timesPushA, timesPushB)
        return if (res.isSolution()) res else null
    }
}
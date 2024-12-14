package org.example.day13

import java.math.BigDecimal

data class Button(val moveX: Long, val moveY: Long) {
    companion object {
        fun from(input: String) : Button {
            val findX: MatchResult = "X\\+([0-9]+)".toRegex().find(input)!!
            val x = findX.groups[1]!!.value.toLong()
            val findY: MatchResult = "Y\\+([0-9]+)".toRegex().find(input)!!
            val y = findY.groups[1]!!.value.toLong()

            return Button(x, y)

        }
    }
}

package org.example.day11

import arrow.core.memoize

fun howManyStones(inputRows: List<String>, times: Int) : Long {
    val input = inputRows[0].split(" ").map { it.toLong() }
    val res = input.sumOf { blinkMemo(it, times) }

    return res
}

fun Long.isZero() : Boolean = this == 0L

fun Long.splitFirst() : Long = this.toString().substring(0, this.toString().length / 2).toLong()
fun Long.splitSecond() : Long = this.toString().substring(this.toString().length / 2, this.toString().length).toLong()

fun blink(it: Long, n: Int) : Long {
    if (n == 0) return listOf(it).size.toLong()
    return when {
            it.isZero() -> blinkMemo(1L, n - 1)
            it.toString().length % 2 == 0 ->  blinkMemo(it.splitFirst(), n - 1) + blinkMemo(it.splitSecond(), n - 1)
            else -> blinkMemo(it * 2024, n - 1)
        }
}

val blinkMemo = ::blink.memoize()
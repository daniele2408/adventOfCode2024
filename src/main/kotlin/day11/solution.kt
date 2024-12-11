package org.example.day11

fun howManyStones(inputRows: List<String>, times: Int) : Int {
    var input = inputRows[0].split(" ").map { it.toLong() }

    repeat(times) {
        input = input.blink()
    }

    return input.size
}

fun Long.isZero() : Boolean = this == 0L

fun Long.splitFirst() : Long = this.toString().substring(0, this.toString().length / 2).toLong()
fun Long.splitSecond() : Long = this.toString().substring(this.toString().length / 2, this.toString().length).toLong()
fun Long.splitInTwo() : List<Long> = listOf(this.splitFirst(), this.splitSecond())

fun List<Long>.blink() : List<Long> {
    return this.map {
        when {
            it.isZero() -> listOf(1L)
            it.toString().length % 2 == 0 -> it.splitInTwo()
            else -> listOf(it * 2024)
        }
    }.flatten()
}

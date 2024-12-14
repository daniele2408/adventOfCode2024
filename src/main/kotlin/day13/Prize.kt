package org.example.day13

data class Prize(val x: Long, val y: Long) {
    companion object {
        fun from(input: String, startOff: Long = 0) : Prize {
            val findX: MatchResult = "X=([0-9]+)".toRegex().find(input)!!
            val x = findX.groups[1]!!.value.toLong()
            val findY: MatchResult = "Y=([0-9]+)".toRegex().find(input)!!
            val y = findY.groups[1]!!.value.toLong()

            return Prize(x + startOff, y + startOff)

        }
    }
}
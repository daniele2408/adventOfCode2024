package org.example.day03

class ConditionalParser(val instruction: String) {

    private val doOperator = "do()"
    private val dontOperator = "don't()"

    fun collectDosAndDonts(cursor: Int, acc : Set<IndexedBool>) : InstructionFilter {
        val nextPosDo = instruction.substring(cursor).indexOf(doOperator)
        val nextPosDont = instruction.substring(cursor).indexOf(dontOperator)
        if (instruction.length < doOperator.length || (nextPosDont == -1 && nextPosDo == -1))
            return InstructionFilter.from(acc)

        val closerOperatorPosition = setOf(
            IndexedBool(true, cursor + nextPosDo),
            IndexedBool(false, cursor + nextPosDont)
        ).filter { it.index > cursor } // filtering not found -1
        .minBy { it.index } // want the closer one

        return collectDosAndDonts(
            closerOperatorPosition.index + 1,
            acc + setOf(closerOperatorPosition)
        )

    }

}
package org.example.day03

typealias IndexedPair = IndexedVal<Pair<Int, Int>>
typealias IndexedBool = IndexedVal<Boolean>

fun IndexedPair.computeScore() : Int {
    return this.value.first * this.value.second
}

fun addingUpParsedMultiplication(inputRows : List<String>) : Int {
    return inputRows.sumOf {
        InstructionParser(it).iterateCollectMultiplicationIndexed(0, listOf())
            .sumOf { idxPair -> idxPair.computeScore() }
    }
}

fun addingUpParsedMultiplicationPart2(inputRows : List<String>) : Int {
    return InstructionParserConditionalSupport(inputRows).parseInstructionsAndGetResult()
}

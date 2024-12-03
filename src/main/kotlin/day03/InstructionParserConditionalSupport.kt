package org.example.day03

class InstructionParserConditionalSupport(private val instructions: List<String>) {

    fun parseInstructionsAndGetResult() : Int {
        return iterateInstructionsLines(instructions, true, 0)
    }

    private fun iterateInstructionsLines(lines: List<String>, startEnabled: Boolean, acc: Int) : Int {
        if (lines.isEmpty()) return acc

        val currentLine = lines[0]
        val operations = InstructionParser(currentLine).iterateCollectMultiplicationIndexed(0, listOf())
        val instructionFilter = ConditionalParser(currentLine).collectDosAndDonts(0, setOf())
        val newAcc = acc + operations.filter { instructionFilter.isEnabled(it, startEnabled) }
            .sumOf { idxPair -> idxPair.computeScore() }

        return iterateInstructionsLines(lines.subList(1, lines.size), instructionFilter.getMaxKeyValue(), newAcc)
    }

}
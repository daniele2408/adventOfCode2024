package org.example.day05

fun addUpMiddlePageNumbersOfCorrectUpdates(inputRows: List<String>) : Int {

    val updateLines = inputRows.filter { ',' in it }.map { UpdateLine.from(it) }
    val rules = PageOrderingRules.from(inputRows)

    return updateLines
        .filter {
            rules.isUpdateLineCorrect(it.update)
        }.sumOf { it.getMiddlePage() }

}

fun addUpMiddlePageNumbersOfEditedInCorrectUpdates(inputRows: List<String>) : Int {

    val updateLines = inputRows.filter { ',' in it }.map { UpdateLine.from(it) }
    val rules = PageOrderingRules.from(inputRows)

    return updateLines
        .filter {
            !rules.isUpdateLineCorrect(it.update)
        }.map {
            it.fixLine(rules)
        }.sumOf { it.getMiddlePage() }

}
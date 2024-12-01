package org.example.day01

import kotlin.math.abs

fun totalListDistance(inputRows: List<String>) : Int {
    val (listFirsts, listSeconds) = extractLists(inputRows)
    return listFirsts.sorted().zip(listSeconds.sorted()).sumOf { abs(it.first - it.second) }
}

fun similarityScore(inputRows: List<String>) : Int {
    val (listFirsts, listSeconds) = extractLists(inputRows)
    val counter = listToCounter(listSeconds)
    return listFirsts.fold(0) { a, b -> a + (counter[b] ?: 0) * b}
}

private fun <T> listToCounter(ls: List<T>) : Map<T, Int> {
    return ls.groupingBy { it }.eachCount()
}

private fun extractLists(inputRows: List<String>) : Pair<List<Int>, List<Int>> {
    val listOfPairs: List<Pair<Int, Int>> = inputRows.map {
        it.split("\\s+".toRegex())
    }.map { ls -> ls[0].toInt() to ls[1].toInt() }.toList()

    return listOfPairs.map { it.first }.toList() to listOfPairs.map { it.second }.toList()
}
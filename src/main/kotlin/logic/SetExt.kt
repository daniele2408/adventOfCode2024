package org.example.logic

fun <T> Set<T>.permutationOneAndRest() : List<PairElAndRest<T>> {
    return this.toList().permutationOneAndRest()
}
package org.example.logic

typealias PairElAndRest<T> = Pair<T, List<T>>

fun <T> List<T>.elementAndAfterIt(ith: Int) : PairElAndRest<T> {
    return this[ith] to this.filterIndexed { idx, _ -> idx > ith }.toList()
}

fun <T> List<T>.elementAndBeforeIt(ith: Int) : PairElAndRest<T> {
    return this[ith] to this.filterIndexed { idx, _ -> idx < ith }.toList()
}

fun <T> List<T>.elementAndRest(ith: Int) : PairElAndRest<T> {
    return this[ith] to this.filterIndexed { idx, _ -> idx != ith }.toList()
}

fun <T> List<T>.permutationOneAndRest() : List<PairElAndRest<T>> {
    return List(this.size) { idx ->
        val (el, rest) = this.elementAndRest(idx)
        Pair(el, rest)
    }.toList()
}


fun <T> List<T>.butLast() : List<T> {
    return when(this.size) {
        0, 1 -> this
        else -> this.subList(0, this.size-1)
    }
}
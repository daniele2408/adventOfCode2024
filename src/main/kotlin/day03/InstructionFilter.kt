package org.example.day03

import java.util.SortedMap

class InstructionFilter(private val data: SortedMap<Int, Boolean>) {
    fun isEnabled(input: IndexedPair, default: Boolean) : Boolean {
        val keyLessEqThan = data.keys.reversed().firstOrNull { it < input.index }
        if (keyLessEqThan == null) return default
        return data[keyLessEqThan]!!
    }

    fun getMaxKeyValue() : Boolean {
        return data[data.keys.max()]!!
    }

    companion object {
        fun from(inputs : Set<IndexedBool>) : InstructionFilter {
            return InstructionFilter(
                inputs.associate { it.index to it.value }.toSortedMap()
            )
        }
    }
}
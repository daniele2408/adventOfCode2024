package org.example.day05

import org.example.permutationOneAndRest

data class UpdateLine(val update: List<Int>) {
    companion object {
        fun from(inputRows: String) : UpdateLine {
            return UpdateLine(inputRows.split(',').map { el -> el.toInt() })
        }
    }

    fun getMiddlePage() : Int {
        return update[(update.size-1)/2]
    }

    fun fixLine(rules: PageOrderingRules) : UpdateLine {
        return editLineToBeCompliant(update.toSet(), rules, listOf())
    }

    private fun editLineToBeCompliant(pages: Set<Int>, rules: PageOrderingRules, acc: List<Int>) : UpdateLine {
        if (pages.size == 1) return UpdateLine(acc + pages)
        val elAndBeforeAndAfter = pages.toList().permutationOneAndRest()
            .find { pair ->
                val (el, rest) = pair
                el in rules.rules.keys && rules.isRuleCompliant(el, listOf(), rest)
            }
        val el = elAndBeforeAndAfter!!.first
        return editLineToBeCompliant(
            pages.filter { it != el }.toSet(),
            rules,
            acc + listOf(el)
        )
    }

}

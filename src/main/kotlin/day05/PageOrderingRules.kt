package org.example.day05

data class PageOrderingRules(val rules: Map<Int, List<Int>>) {

    companion object {
        fun from(inputRows: List<String>) : PageOrderingRules {
            val data = inputRows.filter { '|' in it }.map {
                val (before, after) = it.split("|")
                before.toInt() to after.toInt()
            }.groupBy (
                { it.first },
                { it.second }
            )
            return PageOrderingRules(data)
        }
    }

    fun isUpdateLineCorrect(updateLine: List<Int>) : Boolean {
        return updateLine.filterIndexed { idx, _ ->
            val (el, elementsAfter) = updateLine.elementAndAfterIt(idx)
            val (_, elementsBefore) = updateLine.elementAndBeforeIt(idx)
            isRuleCompliant(el, elementsBefore, elementsAfter)
        }.size == updateLine.size
    }

    fun isRuleCompliant(element: Int, elementsBefore: List<Int>, elementsAfter: List<Int>) : Boolean {
        val allowedAfter = rules[element] ?: return true
        return elementsBefore.all { it !in allowedAfter } && elementsAfter.all { it in allowedAfter }
    }
}

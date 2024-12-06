package org.example

import org.example.day01.similarityScore
import org.example.day01.totalListDistance
import org.example.day02.howManySafeReports
import org.example.day02.howManySafeReportsWithProblemDampener
import org.example.day03.addingUpParsedMultiplication
import org.example.day03.addingUpParsedMultiplicationPart2
import org.example.day04.howManyTimesCrossXmasOccurs
import org.example.day04.howManyTimesXmasOccurs
import org.example.day05.addUpMiddlePageNumbersOfCorrectUpdates
import org.example.day05.addUpMiddlePageNumbersOfEditedInCorrectUpdates
import org.example.day06.howManyDistinctPosition
import org.structure.SolutionBundle
import org.structure.SolutionPart
import org.structure.SolutionRunner


fun main() {
    val solutionRunner = SolutionRunner(
        "inputs", "sample",
        listOf(
            SolutionBundle(
                1,
                SolutionPart({ totalListDistance(it) }, 11),
                SolutionPart({ similarityScore(it) }, 31)
            ),
            SolutionBundle(
                2,
                SolutionPart({ howManySafeReports(it) }, 2),
                SolutionPart({ howManySafeReportsWithProblemDampener(it) }, 4),
            ),
            SolutionBundle(
                3,
                SolutionPart({ addingUpParsedMultiplication(it) }, 161),
                SolutionPart({ addingUpParsedMultiplicationPart2(it) }, 48)
            ),
            SolutionBundle(
                4,
                SolutionPart({ howManyTimesXmasOccurs(it) }, 18),
                SolutionPart({ howManyTimesCrossXmasOccurs(it) }, 9)
            ),
            SolutionBundle(
                5,
                SolutionPart({ addUpMiddlePageNumbersOfCorrectUpdates(it) }, 143),
                SolutionPart({ addUpMiddlePageNumbersOfEditedInCorrectUpdates(it) }, 123)
            ),
            SolutionBundle(
                6,
                SolutionPart({ howManyDistinctPosition(it) }, 41),
            )
        ),
        skipAllButLast = true
    )

    solutionRunner.run()
}
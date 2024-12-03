package org.example

import org.example.day01.similarityScore
import org.example.day01.totalListDistance
import org.example.day02.howManySafeReports
import org.example.day02.howManySafeReportsWithProblemDampener
import org.example.day03.addingUpParsedMultiplication
import org.example.day03.addingUpParsedMultiplicationPart2
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
            )
        ),
        skipAllButLast = false
    )

    solutionRunner.run()
}
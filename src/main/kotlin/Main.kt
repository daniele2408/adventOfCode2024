package org.example

import org.example.day01.similarityScore
import org.example.day01.totalListDistance
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
        )
    )

    solutionRunner.run()
}
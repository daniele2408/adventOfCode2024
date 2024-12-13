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
import org.example.day06.howManyDifferentPositionsToEnforceLoop
import org.example.day06.howManyDistinctPosition
import org.example.day07.computeTotalCalibrationResult
import org.example.day07.computeTotalCalibrationResultWithPipe
import org.example.day08.howManyAntinodes
import org.example.day08.howManyAntinodesWithResonance
import org.example.day09.computeCheckSum
import org.example.day09.computeCheckSumWholeFiles
import org.example.day10.sumRatings
import org.example.day10.sumScores
import org.example.day11.howManyStones
import org.example.day12.totalFencingPrice
import org.structure.SolutionBundle
import org.structure.SolutionPart
import org.structure.SolutionRunner
import java.math.BigInteger


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
                SolutionPart({ howManyDifferentPositionsToEnforceLoop(it) }, 6),
            ),
            SolutionBundle(
                7,
                SolutionPart({ computeTotalCalibrationResult(it) }, BigInteger("3749")),
                SolutionPart({ computeTotalCalibrationResultWithPipe(it) }, BigInteger("11387")),
            ),
            SolutionBundle(
                8,
                SolutionPart({ howManyAntinodes(it) }, 14),
                SolutionPart({ howManyAntinodesWithResonance(it) }, 34),
            ),
            SolutionBundle(
                9,
                SolutionPart({ computeCheckSum(it) }, BigInteger("1928")),
                SolutionPart({ computeCheckSumWholeFiles(it) }, BigInteger("2858"))
            ),
            SolutionBundle(
                10,
                SolutionPart({ sumScores(it) }, 36),
                SolutionPart({ sumRatings(it) }, 81)
            ),
            SolutionBundle(
                11,
                SolutionPart({ howManyStones(it, 25) }, 55312L),
                SolutionPart({ howManyStones(it, 75) }, 65601038650482L) // even if passing, way too slow :( fix if possible
            ),
            SolutionBundle(
                12,
                SolutionPart({ totalFencingPrice(it) }, 1930)
            )

        ),
        skipAllButLast = true
    )

    solutionRunner.run()
}
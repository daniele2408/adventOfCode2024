package org.example.day09

import java.math.BigInteger

fun computeCheckSum(inputRows: List<String>) : BigInteger {
    val input = inputRows[0]
    val block = turnDiskMapToBlock(input)
    val blockShifted = rearrangeBlock(block)
    return computeChecksumBlock(blockShifted)
}

fun turnDiskMapToBlock(diskMap: String) : List<Int> {
    return diskMap.flatMapIndexed { idx, d ->
        if (idx % 2 == 1) generateSequence { -1 }.take(d.digitToInt()).toList()
        else generateSequence { idx / 2 }.take(d.digitToInt()).toList()
    }.toList()
}

fun findMidPoint(block: List<Int>) : Int {
    return block.indices.first { idx ->
        block.subList(0, idx).count { it == -1 } ==
                block.subList(idx, block.size).count { it != -1 }
    }
}

fun rearrangeBlock(block: List<Int>) : List<Int> {
    val midPoint = findMidPoint(block)
    val toReArrange = block.subList(0, midPoint)
    val rest = ArrayDeque(block.subList(midPoint, block.size).filter { it != -1 }.reversed().toList())
    return toReArrange.map { c ->
        if (c == -1) rest.removeFirst()
        else c
    }
}


fun computeChecksumBlock(block: List<Int>) : BigInteger {
    return block.filter { it != -1 }.mapIndexed { idx, c -> idx.toBigInteger() * c.toBigInteger() }
        .reduce { acc, bigInteger -> acc.add(bigInteger) }
}



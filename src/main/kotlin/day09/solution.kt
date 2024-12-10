package org.example.day09

import java.math.BigInteger

fun computeCheckSum(inputRows: List<String>) : BigInteger {
    val input = inputRows[0]
    val block = turnDiskMapToBlock(input)
    val blockShifted = rearrangeBlock(block)
    return computeChecksumBlock(blockShifted)
}

fun computeCheckSumWholeFiles(inputRows: List<String>) : BigInteger {
    val input = inputRows[0]
    val block = turnDiskMapToArrayBlock(input)
    val blockShifted = rearrangeBlockWhole(block)
    return computeChecksumBlock(blockShifted) // 84849197273
}

fun turnDiskMapToBlock(diskMap: String) : List<Int> {
    return diskMap.flatMapIndexed { idx, d ->
        if (idx % 2 == 1) generateSequence { -1 }.take(d.digitToInt()).toList()
        else generateSequence { idx / 2 }.take(d.digitToInt()).toList()
    }.toList()
}

fun turnDiskMapToArrayBlock(diskMap: String) : Array<Block> {
    return diskMap.mapIndexed { idx, d ->
        if (idx % 2 == 1) Block(-1, d.digitToInt())
        else Block(idx / 2, d.digitToInt())
    }.filter { it.size != 0 }.toTypedArray()
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

fun rearrangeBlockWhole(block: Array<Block>) : Array<Block> {

    val ids = block.filter { it.id != -1 }.map { it.id }.sorted().reversed()
    return rearrangeBlock(block, ids)
}

fun rearrangeBlock(block: Array<Block>, ids: List<Int>) : Array<Block> {
    if (ids.isEmpty()) return block

    val idx = ids.first()
    val rest = ids.subList(1, ids.size)

    val currentBlockIdx = block.indexOfFirst { it.id == idx }

    val emptyIdx = block.sliceArray(0..<currentBlockIdx).indexOfFirst { it.id == -1 && it.size >= block[currentBlockIdx].size }

    return if (emptyIdx == -1) {
        rearrangeBlock(block, rest)
    }
    else {
        val newBlock = swapPositionsArrayBlock(block, emptyIdx, currentBlockIdx)
        rearrangeBlock(
            newBlock,
            rest
        )
    }

}

fun swapPositionsArrayBlock(array: Array<Block>, toIdx: Int, fromIdx: Int) : Array<Block> {
    val emptyChunk = array[toIdx]
    val fullChunk = array[fromIdx]
    val availableSpace = emptyChunk.size - fullChunk.size
    val newChunk = if (availableSpace == 0) arrayOf(fullChunk.copy())
    else arrayOf(fullChunk.copy(), Block(-1, availableSpace))

    return array.sliceArray(0..<toIdx) + mergeArrays(
        newChunk, mergeArrays(
            array.sliceArray(toIdx+1..<fromIdx),
            mergeArrays(arrayOf(Block(-1, fullChunk.size)), array.sliceArray(fromIdx+1..<array.size))
        )
    )
}

fun mergeArrays(first: Array<Block>, second: Array<Block>) : Array<Block> {
    if (first.isEmpty()) return second
    if (second.isEmpty()) return first
    if (first.last().id == -1 && second.first().id == -1) {
        return first.sliceArray(0..<first.size-1) + Block(-1, first.last().size + second.first().size) + second.sliceArray(1..<second.size)
    }
    return first + second
}

fun computeChecksumBlock(block: Collection<Int>) : BigInteger {
    return block.filter { it != -1 }.mapIndexed { idx, c -> idx.toBigInteger() * c.toBigInteger() }
        .reduce { acc, bigInteger -> acc.add(bigInteger) }
}

fun computeChecksumBlock(block: Array<Block>) : BigInteger {
    return block.flatMap { generateSequence { it.id }.take(it.size) }
        .mapIndexed {
                    idx, c ->
            if (c != -1) idx.toBigInteger() * c.toBigInteger() else BigInteger.ZERO
        }
        .reduce { acc, bigInteger -> acc.add(bigInteger) }
}



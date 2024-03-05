package day3.b.after

import java.io.File

fun main() {
    val data: List<String> =
        File("src/AoC-2023/resources/AoC.2023.12.03.txt").readLines()
    println(checkSchematics(data))
}

private val nonSpecialChars = setOf('0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '.')

fun checkSchematics(input: List<String>): Int {
    return input
        .let { Schematic(it).gearRatios }
        .sum()
}

class Schematic(val input: List<String>) {
    val numberRegex = "\\d+".toRegex()
    val gearRegex = Regex.fromLiteral("*")
    val partNumbers: List<Int>
    val gearRatios: List<Int>

    init {
        partNumbers = findAll(numberRegex)
            .filter { isBoundedBySpecialChars(it) }
            .map { (_, match) -> match.value.toInt() }

        gearRatios = findAll(gearRegex)
            .map { getBoundingNumbers(it) }
            .filter { it.size == 2 }
            .map { toGearRatio(it) }
    }

    private fun findAll(regex: Regex): List<Pair<Int, MatchResult>> =
        input.flatMapIndexed { index, s -> regex.findAll(s).map { index to it } }

    private fun isBoundedBySpecialChars(indexMatch: Pair<Int, MatchResult>): Boolean {
        val (rowRange, colRange) = getBoundingRectangle(indexMatch)
        return rowRange.any { row -> colRange.any { col -> input[row][col] !in nonSpecialChars } }
    }

    private fun getBoundingRectangle(indexMatch: Pair<Int, MatchResult>): Pair<IntRange, IntRange> {
        val (index, match) = indexMatch
        val rowRange = ((index - 1)..(index + 1)).filter { row -> row in input.indices }
        val colRange = ((match.range.first - 1)..(match.range.last + 1)).filter { col -> col in input[0].indices }
        return rowRange.asIntRange() to colRange.asIntRange()
    }

    private fun getBoundingNumbers(indexMatch: Pair<Int, MatchResult>): List<Int> {
        val (rowRange, colRange) = getBoundingRectangle(indexMatch)
        return rowRange.flatMap { numberRegex.findAll(input[it]) }
            .filter { mr -> mr.range.isOverlappingWith(colRange) }
            .map { mr -> mr.value.toInt() }
    }

    private fun toGearRatio(it: List<Int>) = it[0] * it[1]
}

fun IntRange.isOverlappingWith(range: IntRange): Boolean {
    return range.first() <= this.last && range.last() >= this.first
}

fun List<Int>.asIntRange(): IntRange {
    return this.first()..this.last()
}

//Changes inspired by "https://github.com/mfedirko/advent-of-code-2023/blob/main/src/main/kotlin/io/mfedirko/aoc/day03/Day3.kt"
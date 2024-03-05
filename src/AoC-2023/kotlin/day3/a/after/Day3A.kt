import java.io.File

fun main() {
    val schematicData: List<String> =
        File("src/AoC-2023/resources/AoC.2023.12.03.txt").readLines()
    println(checkSchematics(schematicData))
}

private val nonSpecialChars = setOf('0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '.')

fun checkSchematics(input: List<String>): Int {
    return input
        .let { Schematic(it).partNumbers }
        .sum()
}

class Schematic(val schematicData: List<String>) {
    private val numberRegex = "\\d+".toRegex()
    val partNumbers: List<Int>

    init {
        partNumbers = findAll(numberRegex)
            .filter { isBoundedBySpecialChars(it) }
            .map { (_, match) -> match.value.toInt() }
    }

    private fun findAll(regex: Regex): List<Pair<Int, MatchResult>> =
        schematicData.flatMapIndexed { index, s -> regex.findAll(s).map { index to it } }

    private fun isBoundedBySpecialChars(indexMatch: Pair<Int, MatchResult>): Boolean {
        val (rowRange, colRange) = getBoundingRectangle(indexMatch)
        return rowRange.any { row -> colRange.any { col -> isSpecialCharacter(row, col) } }
    }

    private fun isSpecialCharacter(row: Int, col: Int): Boolean =
        schematicData[row][col] !in nonSpecialChars

    private fun getBoundingRectangle(indexMatch: Pair<Int, MatchResult>): Pair<IntRange, IntRange> {
        val (index, match) = indexMatch
        val rowRange = ((index - 1)..(index + 1)).filter { row -> row in schematicData.indices }
        val colRange =
            ((match.range.first - 1)..(match.range.last + 1)).filter { col -> col in schematicData[0].indices }
        return rowRange.asIntRange() to colRange.asIntRange()
    }
}

fun List<Int>.asIntRange(): IntRange {
    return this.first()..this.last()
}

//Changes inspired by "https://github.com/mfedirko/advent-of-code-2023/blob/main/src/main/kotlin/io/mfedirko/aoc/day03/Day3.kt"
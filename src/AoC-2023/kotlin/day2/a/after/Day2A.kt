package day2.a.after

import java.io.File

fun main() {
    val data: List<String> =
        File("src/AoC-2023/resources/AoC.2023.12.02.txt").readLines()
    println(countGames(data))
}

fun countGames(input: List<String>): Int {
    val actualCounts = mapOf("red" to 12, "green" to 13, "blue" to 14)
    return input
        .map { parseGame(it) }
        .filter { it.isPossible(actualCounts) }
        .map { it.id }
        .sum()
}

fun parseGame(line: String): Game {
    val id: Int = line.substring("Game ".length, line.indexOf(':')).toInt()
    val counts = line.substringAfter(": ").split(", ", "; ").groupBy(
        { segment -> segment.split(' ')[1] },
        { segment -> segment.split(' ')[0].toInt()}
    )
    return Game(id, counts)
}

class Game(val id: Int, counts: Map<String, List<Int>>) {
    private val max: Map<String, Int>
    init {
        val maxPairs: Array<Pair<String, Int>> = counts.entries.map { e -> e.key to e.value.max() }.toTypedArray()
        max = mapOf(*maxPairs)
    }

    fun isPossible(actual: Map<String, Int>): Boolean {
        return max.all { e -> (actual[e.key] ?: 0) >= e.value }
    }
}

//Inspired by "https://github.com/mfedirko/advent-of-code-2023/blob/main/src/main/kotlin/io/mfedirko/aoc/day02/Day2.kt"
package day1.a.after

import java.io.File

fun main() {
    val data: List<String> =
        File("src/AoC-2023/resources/AoC.2023.12.01.txt").readLines()
    println(getTotal(data))
}

fun getTotal(input: List<String>): Int {
    return input
        .map { it.first { ch -> ch.isDigit() }.digitToInt() * 10 + it.last { ch -> ch.isDigit() }.digitToInt() }
        .sum()
}

//Inspired by "https://github.com/mfedirko/advent-of-code-2023/blob/main/src/main/kotlin/io/mfedirko/aoc/day01/Day1.kt"
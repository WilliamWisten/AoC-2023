package day1.b.after

import java.io.File


fun main(args: Array<String>) {
    val data: List<String> =
        File("src/AoC-2023/resources/AoC.2023.12.01.txt").readLines()
    println(getCalibrationValue(data))
}
val strToDigit = mapOf(
    "one" to 1, "1" to 1, "two" to 2, "2" to 2, "three" to 3, "3" to 3,
    "four" to 4, "4" to 4, "five" to 5, "5" to 5, "six" to 6, "6" to 6,
    "seven" to 7, "7" to 7, "eight" to 8, "8" to 8, "nine" to 9, "9" to 9
)

val allDigits = strToDigit.keys

fun getCalibrationValue(input: List<String>): Int {
    return input
        .map { toInt(it.findAnyOf(allDigits)!!) * 10 + toInt(it.findLastAnyOf(allDigits)!!) }
        .sum()
}

fun toInt(pair: Pair<Int, String>): Int {
    return strToDigit[pair.second]!!
}

//Inspired by "https://github.com/mfedirko/advent-of-code-2023/blob/main/src/main/kotlin/io/mfedirko/aoc/day01/Day1.kt"

package day1.a.before

import java.io.File

fun main() {
    val data: List<String> =
        File("src/AoC-2023/resources/AoC.2023.12.01.txt").readLines()
    var sum = 0
    for(i in data.indices){
        sum += getCalibrationValue(data[i])
    }
    println(sum)
}

fun getCalibrationValue(input: String): Int {
    val tempArray = input.toCharArray()
    val firstDigit = (tempArray.first { c: Char ->  48 <= c.code && c.code <= 57})
    val secondDigit = (tempArray.last { c: Char ->  48 <= c.code && c.code <= 57})
    return "$firstDigit$secondDigit".toInt()
}
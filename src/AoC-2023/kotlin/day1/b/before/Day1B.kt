package day1.b.before

import java.io.File


fun main(args: Array<String>) {
    val data: List<String> =
        File("src/AoC-2023/resources/AoC.2023.12.01.txt").readLines()
    var sum = 0
    for(i in data.indices){
        sum += getCalibrationValue(data.get(i))
    }
    println(sum)
}

fun getCalibrationValue(input: String): Int {
    val numbers = listOf("zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine")
    val firstDigit: Int
    val secondDigit: Int

    val tempArray = input.toCharArray()
    val firstDigitIndex = (tempArray.indexOfFirst { c: Char ->  48 <= c.code && c.code <= 57})
    val secondDigitIndex = (tempArray.indexOfLast { c: Char ->  48 <= c.code && c.code <= 57})

    val firstDigitIndexText = input.findAnyOf(numbers)
    val secondDigitIndexText = input.findLastAnyOf(numbers)

    if (firstDigitIndexText != null) {
        if (firstDigitIndex < firstDigitIndexText.first)
            firstDigit = tempArray.get(firstDigitIndex).digitToInt()
        else
            firstDigit = numbers.indexOf(firstDigitIndexText.second)
    }
    else
        firstDigit = tempArray.get(firstDigitIndex).digitToInt()
    if (secondDigitIndexText != null) {
        if (secondDigitIndex > secondDigitIndexText.first)
            secondDigit = tempArray.get(secondDigitIndex).digitToInt()
        else
            secondDigit = numbers.indexOf(secondDigitIndexText.second)
    }
    else
        secondDigit = tempArray.get(secondDigitIndex).digitToInt()

    return "$firstDigit$secondDigit".toInt()
}

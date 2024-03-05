package day3.b.before

import java.io.File

fun main() {
    val data: List<String> =
        File("src/AoC-2023/resources/AoC.2023.12.03.txt").readLines()
    println(checkNumbers(data))
}

fun checkNumbers(input: List<String>): Long {
    var total: Long = 0
    for (i in input.indices) {
        val tempArray = input.get(i).toCharArray()
        for (j in tempArray.indices) {
            if (isStar(tempArray[j])) {
                total += checkStar(input, i, j)
            }
        }
    }
    return total
}


fun checkStar(input: List<String>, line: Int, starIndex: Int): Int {
    var hasOneNumber = false
    var firstNumber= 0
    val secondNumber: Int
    if (starIndex != 0 && isNumber(input[line][starIndex - 1])) {
            firstNumber = getWholeNumber(input[line].toCharArray(), starIndex - 1)
            hasOneNumber = true
    }
    if (starIndex != input[line].length - 1 && isNumber(input[line][starIndex + 1])) {
        if (hasOneNumber) {
            secondNumber = getWholeNumber(input[line].toCharArray(), starIndex + 1)
            return firstNumber * secondNumber
        } else {
            firstNumber = getWholeNumber(input[line].toCharArray(), starIndex + 1)
            hasOneNumber = true
        }
    }
    if (line != 0) {
        if (starIndex != 0) {
            if (isNumber(input[line - 1][starIndex - 1])) {
                if (hasOneNumber) {
                    secondNumber = getWholeNumber(input[line - 1].toCharArray(), starIndex - 1)
                    return firstNumber * secondNumber
                } else {
                    firstNumber = getWholeNumber(input[line - 1].toCharArray(), starIndex - 1)
                    hasOneNumber = true
                }
            }
        }
        if (isNumber(input[line - 1][starIndex]) && !isNumber(input[line - 1][starIndex - 1])) {
            if (hasOneNumber) {
                secondNumber = getWholeNumber(input[line - 1].toCharArray(), starIndex)
                return firstNumber * secondNumber
            } else {
                firstNumber = getWholeNumber(input[line - 1].toCharArray(), starIndex)
                hasOneNumber = true
            }
        }
        if (starIndex != input[line].length - 1 && !isNumber(input[line - 1][starIndex])) {
            if (isNumber(input[line - 1][starIndex + 1])) {
                if (hasOneNumber) {
                    secondNumber = getWholeNumber(input[line - 1].toCharArray(), starIndex + 1)
                    return firstNumber * secondNumber
                } else {
                    firstNumber = getWholeNumber(input[line - 1].toCharArray(), starIndex + 1)
                    hasOneNumber = true
                }
            }
        }
    }
    if (line != input.size - 1) {
        if (starIndex != 0) {
            if (isNumber(input[line + 1][starIndex - 1])) {
                if (hasOneNumber) {
                    secondNumber = getWholeNumber(input[line + 1].toCharArray(), starIndex - 1)
                    return firstNumber * secondNumber
                } else {
                    firstNumber = getWholeNumber(input[line + 1].toCharArray(), starIndex - 1)
                    hasOneNumber = true
                }
            }
        }
        if (isNumber(input[line + 1][starIndex]) && !isNumber(input[line + 1][starIndex - 1])) {
            if (hasOneNumber) {
                secondNumber = getWholeNumber(input[line + 1].toCharArray(), starIndex)
                return firstNumber * secondNumber
            } else {
                firstNumber = getWholeNumber(input[line + 1].toCharArray(), starIndex)
                hasOneNumber = true
            }
        }
        if (starIndex != input[line].length - 1 && !isNumber(input[line + 1][starIndex])) {
            if (isNumber(input[line + 1][starIndex + 1])) {
                if (hasOneNumber) {
                    secondNumber = getWholeNumber(input[line + 1].toCharArray(), starIndex + 1)
                    return firstNumber * secondNumber
                }
            }
        }
    }
    return 0
}

fun isNumber(c: Char): Boolean {
    return (c.code in 48..57)
}

fun getWholeNumber(charArray: CharArray, index: Int): Int {
    var leftDone = false
    var rightDone = false
    var startIndex = index
    var endIndex = index + 1
    var counter = 1
    while (!rightDone) {
        if (!leftDone) {
            if ((index - counter) != -1 && isNumber(charArray[index - counter])) {
                startIndex -= 1
                counter++
            } else {
                counter = 1
                leftDone = true
            }
        } else {
            if ((index + counter) != charArray.size && isNumber(charArray[index + counter])) { //Hade skrivit charArray.size - 1 och var fast i 1 timme
                endIndex += 1
                counter++
            } else {
                rightDone = true
            }
        }
    }
    val s = String(charArray).substring(startIndex, endIndex)
    return s.toInt()
}

fun isStar(c: Char): Boolean {
    return (c.code == 42)
}
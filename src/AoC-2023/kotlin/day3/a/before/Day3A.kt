package day3.a.before

import java.io.File

fun main() {
    val data: List<String> =
        File("src/AoC-2023/resources/AoC.2023.12.03.txt").readLines()
    println(checkNumbers(data))
}

fun checkNumbers(input: List<String>): Int {
    var total = 0
    for (i in input.indices) {
        val tempArray = input.get(i).toCharArray()
        var startIndex = 0
        var endIndex = 0
        var ongoingNumber = false
        for (j in tempArray.indices) {
            if (isNumber(tempArray[j]) && !ongoingNumber) {
                startIndex = j
                ongoingNumber = true
            }
            else if ((!isNumber(tempArray[j]) && ongoingNumber)) {
                endIndex = j - 1
                total += checkSymbols(input, i, startIndex, endIndex)
                ongoingNumber = false
            }
            else if(ongoingNumber && j == tempArray.size - 1){
                endIndex = j
                total += checkSymbols(input, i, startIndex, endIndex)
                ongoingNumber = false
            }
        }
    }
    return total
}


fun checkSymbols(data: List<String>, line: Int, startIndex: Int, endIndex: Int): Int {
    var hasSymbol = false
    if(startIndex != 0 && (data[line][startIndex - 1].code != 46) ||
        endIndex != data[line].length - 1 && (data[line][endIndex + 1].code != 46)){
        hasSymbol = true
    }
    if (line != 0){
        if(startIndex != 0){
            if (data[line - 1][startIndex-1].code != 46){
                hasSymbol = true
            }
        }
        if(endIndex != data[line].length - 1){
            if (data[line - 1][endIndex + 1].code != 46){
                hasSymbol = true
            }
        }
        for (i in (startIndex)..(endIndex)){
            if (data[line - 1][i].code != 46){
                hasSymbol = true
            }
        }
    }
    if (line != data.size - 1){
        if(startIndex != 0){
            if (data[line + 1][startIndex-1].code != 46){
                hasSymbol = true
            }
        }
        if(endIndex != data[line].length - 1){
            if (data[line + 1][endIndex + 1].code != 46){
                hasSymbol = true
            }
        }
        for (i in (startIndex)..(endIndex)){
            if (data[line + 1][i].code != 46){
                hasSymbol = true
            }
        }
    }
    if(hasSymbol) {
        return data[line].substring(startIndex, endIndex + 1).toInt()
    }
    return 0
}

fun isNumber(c: Char): Boolean {
    return (c.code in 48..57)
}
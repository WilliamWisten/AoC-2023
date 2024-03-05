package day2.a.before

import java.io.File

fun main() {
    val data: List<String> =
        File("src/AoC-2023/resources/AoC.2023.12.02.txt").readLines()
    var sum = 0
    for (i in data.indices) {
        sum += checkRound(data[i].trim())
    }
    println(sum)
}

fun checkRound(input: String): Int {
    val id = input.substring((input.indexOf(" ") + 1), input.indexOf(":")).toInt()
    val rounds = input.substring((input.indexOf(":") + 2)).split(";").toList()
    var valid = true
    for (i in rounds.indices) {
        val dices = rounds.get(i).split(",").toList()
        for (i in dices.indices) {
            val dice = dices[i].trim()
            val color = dice.substring(dice.indexOf(" ")).trim()
            val amount = dice.trim().substring(0, (dice.indexOf(" "))).toInt()
            if (color.equals("red", true) && amount > 12) {
                valid = false
            } else if (color.equals("green", true) && amount > 13) {
                valid = false
            } else if (color.equals("blue", true) && amount > 14) {
                valid = false
            }
        }
    }
    if (valid) {
        return id
    }
    return 0
}
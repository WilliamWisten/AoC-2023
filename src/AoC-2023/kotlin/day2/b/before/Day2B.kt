package day2.b.before

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
    val rounds = input.substring((input.indexOf(":") + 2)).split(";").toList()
    var green = 0
    var blue = 0
    var red = 0
    for (i in rounds.indices) {
        val dices = rounds.get(i).split(",").toList()
        for (i in dices.indices) {
            val dice = dices[i].trim()
            val color = dice.substring(dice.indexOf(" ")).trim()
            val amount = dice.trim().substring(0, (dice.indexOf(" "))).toInt()
            if (color.equals("red", true) && amount > red) {
                red = amount
            } else if (color.equals("green", true) && amount > green) {
                green = amount
            } else if (color.equals("blue", true) && amount > blue) {
                blue = amount
            }
        }
    }
    return green * blue * red
}
package com.example.testavito.Data

import kotlin.random.Random

class Pull {

    companion object {

        private var numbers = mutableListOf(
            16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30)

        private val start_size = numbers.size

        fun deleteNumber(n: String) {
            numbers.remove(n.toInt())
        }

        fun getNumber(): String {
            if (numbers.size != 0) return numbers[Random.nextInt(0, numbers.size)].toString()
            else return ""
        }

        fun getSize(): Int {
            return numbers.size
        }

        fun getStartSize(): Int {
            return start_size
        }
    }
}
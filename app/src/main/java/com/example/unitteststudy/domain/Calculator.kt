package com.example.unitteststudy.domain

class Calculator {

    fun sum(numA: Int, numB: Int): Int {
        return numA + numB
    }

    fun sub(numA: Int, numB: Int): Int {
        return numA - numB
    }

    fun multi(numA: Int, numB: Int): Int {
        return numA * numB
    }

    fun div(numA: Int, numB: Int): Int? {
        return if (numB > 0) {
            numA / numB
        } else null
    }
}
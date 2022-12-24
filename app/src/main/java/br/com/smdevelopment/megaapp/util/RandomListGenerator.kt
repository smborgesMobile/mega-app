package br.com.smdevelopment.megaapp.util

interface RandomListGenerator {

    fun createRandomGame(amountOfNumbers: Int, startRange: Int, endRange: Int): String
}
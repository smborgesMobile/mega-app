package br.com.smdevelopment.megaapp.util

class RandomListGeneratorImpl : RandomListGenerator {

    override fun createRandomGame(amountOfNumbers: Int, startRange: Int, endRange: Int): String {
        val setOfMutable: MutableSet<Int> = mutableSetOf()
        while (setOfMutable.size < amountOfNumbers) {
            setOfMutable.add((startRange..endRange).random())
        }

        return setOfMutable.toList().sorted().joinToString(SEPARATOR) {
            it.toString().padStart(LENGTH, START_PADDING)
        }
    }

    private companion object {
        const val SEPARATOR = " "
        const val LENGTH = 2
        const val START_PADDING = '0'
    }
}
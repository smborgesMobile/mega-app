package br.com.smdevelopment.megaapp.screens.home

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import br.com.smdevelopment.megaapp.util.RandomListGenerator
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val randomUtil: RandomListGenerator) : ViewModel() {

    val numberList = mutableStateListOf<String>()

    fun generateNumbers(numberOfChances: String) {
        val numberToInt = numberOfChances.toInt()
        numberList.add(randomUtil.createRandomGame(numberToInt, INITIAL_RANGE, END_RANGE))
    }

    private companion object {
        const val INITIAL_RANGE = 1
        const val END_RANGE = 60
    }
}
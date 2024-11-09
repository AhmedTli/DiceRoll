package com.example.diceroll.viewmodel

import androidx.compose.runtime.mutableIntStateOf
import androidx.lifecycle.ViewModel
import kotlin.random.Random

class DiceViewModel : ViewModel() {
    var diceValue = mutableIntStateOf(1)
        private set

    var player1Points = mutableIntStateOf(0)
        private set

    var player2Points = mutableIntStateOf(0)
        private set

    var currentPlayer = mutableIntStateOf(1)


    fun rollDice() {
        diceValue.intValue = Random.nextInt(1, 7)
        updatePoints()
    }

    private fun updatePoints() {
        if (currentPlayer.intValue == 1) {
            player1Points.intValue += diceValue.intValue
            currentPlayer.intValue = 2
        } else {
            player2Points.intValue += diceValue.intValue
            currentPlayer.intValue = 1
        }
    }

    fun resetGame() {
        player1Points.intValue = 0
        player2Points.intValue = 0
        currentPlayer.intValue = 1
        diceValue.intValue = 1
    }
}

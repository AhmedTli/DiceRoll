package com.example.diceroll

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.diceroll.model.DiceViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DiceRoll()
        }
    }
}

@Composable
fun DiceRoll(viewModel: DiceViewModel = viewModel()) {
    val diceValue by viewModel.diceValue
    val player1Points by viewModel.player1Points
    val player2Points by viewModel.player2Points
    val currentPlayer by viewModel.currentPlayer

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Box(
            modifier = Modifier
                .size(150.dp)
                .border(2.dp, Color.Blue, RoundedCornerShape(10.dp))
                .clip(RoundedCornerShape(10.dp))
        ) {
            Image(
                painter = painterResource(id = R.drawable.dice), // Use your drawable resource
                contentDescription = "Dice",
                modifier = Modifier.fillMaxSize(),
            )
        }
        Text(text = "$diceValue", style = MaterialTheme.typography.headlineLarge)

        Spacer(modifier = Modifier.height(20.dp))

        Button(onClick = {
            viewModel.rollDice()
        }) {
            Text(text = "Roll Dice")
        }

        Spacer(modifier = Modifier.height(20.dp))


        Text(text = "Player 1 Points: $player1Points", style = MaterialTheme.typography.headlineMedium)
        Text(text = "Player 2 Points: $player2Points", style = MaterialTheme.typography.headlineMedium)
        Text(text = "Current Player: Player $currentPlayer", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(20.dp))


        Button(onClick = { viewModel.resetGame() }) {
            Text(text = "Reset Game")
        }
    }
}

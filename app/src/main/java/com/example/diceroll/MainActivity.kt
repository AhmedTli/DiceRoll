package com.example.diceroll

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.diceroll.viewmodel.DiceViewModel





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
    var isRolling by remember { mutableStateOf(false) }

    // Rotation angle based on animation state
    val rotationAngle by animateFloatAsState(
        targetValue = if (isRolling) 360f else 0f,  // Rotate 360 degrees
        animationSpec = tween(durationMillis = 100),  // 0.5-second duration
        finishedListener = { isRolling = false }, label = ""  // Reset after animation completes
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "Rolled: $diceValue",
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.padding(bottom = 16.dp)
        )


        Box(
            modifier = Modifier
                .size(150.dp)
                .border(2.dp, Color.Blue, RoundedCornerShape(10.dp))
                .clip(RoundedCornerShape(10.dp))
                .background(Color.LightGray)
                .rotate(rotationAngle)  // Apply rotation animation

        ) {
            Image(
                painter = painterResource(id = R.drawable.dice),
                contentDescription = "Dice",
                modifier = Modifier.fillMaxSize()

            )
        }

        Spacer(modifier = Modifier.height(20.dp))


        Card(
            modifier = Modifier.fillMaxWidth().padding(8.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFE3F2FD)) // Light blue background
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Player 1 Points: $player1Points", style = MaterialTheme.typography.bodyLarge)
            }
        }

        Card(
            modifier = Modifier.fillMaxWidth().padding(8.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFFFEBEE)) // Light pink background
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Player 2 Points: $player2Points", style = MaterialTheme.typography.bodyLarge)
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text(text = "Current Player: Player $currentPlayer", style = MaterialTheme.typography.bodyMedium)

        Spacer(modifier = Modifier.height(20.dp))

        // Roll Dice Button
        Button(onClick = {
            isRolling = true
            viewModel.rollDice() }) {
            Text(text = "Roll Dice")
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Reset Game Button
        Button(onClick = { viewModel.resetGame() }) {
            Text(text = "Reset Game")
        }
    }
}

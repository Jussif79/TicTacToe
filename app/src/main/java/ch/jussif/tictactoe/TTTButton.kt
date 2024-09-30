package ch.jussif.tictactoe


import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun TTTButton(row: Int, column: Int, viewModel: TTTViewModel) {
    val current = viewModel.gameState[row][column]
    val display = if (current == 1) "X" else if (current == -1) "O" else ""

    Button(enabled = current == 0, onClick = { viewModel.makeMove(row, column) }) {
        Text(display)
    }
}
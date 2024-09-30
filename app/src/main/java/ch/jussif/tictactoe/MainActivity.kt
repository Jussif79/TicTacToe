package ch.jussif.tictactoe

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ch.jussif.tictactoe.ui.theme.TicTacToeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TicTacToeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TicTacToe(TTTViewModel())
                }
            }
        }
    }
}

@Composable
fun TicTacToe(viewModel: TTTViewModel) {
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        ServerBar(viewModel)
        Spacer(modifier = Modifier.height(20.dp))
        RegisterBar(viewModel)
        Spacer(modifier = Modifier.height(20.dp))
        LoginBar(viewModel)
        Spacer(modifier = Modifier.height(20.dp))
        GameBar(viewModel)
        Spacer(modifier = Modifier.height(20.dp))
        GameArea(viewModel)
    }
}

@Composable
fun ServerBar(viewModel: TTTViewModel) {
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(text = "Server: " + Base_URL)
        Button(onClick = { viewModel.ping() }) { Text(text = "Ping")}
        Text(text = viewModel.pingResult.toString())
    }
}

@Composable
fun RegisterBar(viewModel: TTTViewModel) {
    var userName by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }


}

@Composable
fun LoginBar(viewModel: TTTViewModel) {

}

@Composable
fun GameBar(viewModel: TTTViewModel) {

}

@Composable
fun GameArea(viewModel: TTTViewModel) {

}
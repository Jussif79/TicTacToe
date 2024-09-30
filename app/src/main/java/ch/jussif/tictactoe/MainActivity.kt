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
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
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
        Button(onClick = { viewModel.ping() }) { Text(text = "Ping") }
        Text(text = viewModel.pingResult.toString())
    }
}

@Composable
fun RegisterBar(viewModel: TTTViewModel) {
    var userName by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }

    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxSize()
    ) {
        TextField(
            value = userName,
            onValueChange = { userName = it },
            label = { Text(text = "User Name") },
            modifier = Modifier.width(120.dp)
        )
        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text(text = "Password") },
            modifier = Modifier.width(120.dp)
        )
        Button(
            enabled = viewModel.pingResult == true,
            onClick = { viewModel.login(userName, password) }) {
            Text("Register")
        }
    }
}

@Composable
fun LoginBar(viewModel: TTTViewModel) {
    var userName by rememberSaveable { mutableStateOf("Jussif") }
    var password by rememberSaveable { mutableStateOf("12345") }

    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxSize()
    ) {
        TextField(
            value = userName,
            onValueChange = { userName = it },
            label = { Text(text = "User Name") },
            modifier = Modifier.width(120.dp)
        )
        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text(text = "Password") },
            modifier = Modifier.width(120.dp)
        )
        Button(
            enabled = viewModel.pingResult == true,
            onClick = { viewModel.login(userName, password) }) {
            Text("Login")
        }
    }
}

@Composable
fun GameBar(viewModel: TTTViewModel) {
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxSize()
    ) {
        Text("Token: ${viewModel.loginToken}")
        Button(enabled = !viewModel.loginToken.isEmpty(), onClick = { viewModel.newGame(viewModel.loginToken)}){
            Text("New Game")
        }
    }
}

@Composable
fun GameArea(viewModel: TTTViewModel) {
    Column(verticalArrangement = Arrangement.Top, horizontalAlignment = Alignment.CenterHorizontally)  {
        Row {
            Column {
                TTTButton(row = 0, column = 0, viewModel = viewModel)
                TTTButton(row = 0, column = 1, viewModel = viewModel)
                TTTButton(row = 0, column = 2, viewModel = viewModel)
            }
            Column {
                TTTButton(row = 1, column = 0, viewModel = viewModel)
                TTTButton(row = 1, column = 1, viewModel = viewModel)
                TTTButton(row = 1, column = 2, viewModel = viewModel)

            }
            Column {
                TTTButton(row = 2, column = 0, viewModel = viewModel)
                TTTButton(row = 2, column = 1, viewModel = viewModel)
                TTTButton(row = 2, column = 2, viewModel = viewModel)
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        Text(if (viewModel.gameOver) "Game Over!" else "Your Turn!")
    }
}
package ch.jussif.tictactoe

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
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
                // A surface container using the 'background' color from the theme
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
fun TicTacToe(tttViewModel: TTTViewModel) {
    Column(
        verticalArrangement = Arrangement.Top, horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        ServerBar(tttViewModel)
        Spacer(modifier = Modifier.height(20.dp))
        RegisterBar(tttViewModel)
        Spacer(modifier = Modifier.height(20.dp))
        LoginBar(tttViewModel)
        Spacer(modifier = Modifier.height(20.dp))
        GameBar(tttViewModel)
        Spacer(modifier = Modifier.height(20.dp))
        GameArea(tttViewModel)
    }
}

@Composable
fun ServerBar(tttViewModel: TTTViewModel) {
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(Base_URL)
        Button(onClick = { tttViewModel.ping() }) { Text("Ping"); }
        Text(tttViewModel.pingResult.toString())
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterBar(tttViewModel: TTTViewModel) {
    var userName by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        TextField(
            value = userName,
            onValueChange = { userName = it },
            label = { Text("User name") },
            modifier = Modifier.width(120.dp)
        )
        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            modifier = Modifier.width(120.dp)
        )
        Button(
            enabled = tttViewModel.pingResult == true,
            onClick = { tttViewModel.register(userName, password) }) { Text("Register") }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginBar(tttViewModel: TTTViewModel) {
    var userName by rememberSaveable { mutableStateOf("Brad") }
    var password by rememberSaveable { mutableStateOf("woof") }
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        TextField(
            value = userName,
            onValueChange = { userName = it },
            label = { Text("User name") },
            modifier = Modifier.width(120.dp)
        )
        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            modifier = Modifier.width(120.dp)
        )
        Button(
            enabled = tttViewModel.pingResult == true,
            onClick = { tttViewModel.login(userName, password) }) { Text("Login") }
    }
}

@Composable
fun GameBar(tttViewModel: TTTViewModel) {
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text("Token: ${tttViewModel.loginToken}")
        Button(
            enabled = !tttViewModel.loginToken.isEmpty(),
            onClick = { tttViewModel.newGame(tttViewModel.loginToken) }) { Text("New game"); }
    }
}

@Composable
fun GameArea(tttViewModel: TTTViewModel) {
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row() {
            Column() {
                TTTButton(0, 0, tttViewModel)
                TTTButton(0, 1, tttViewModel)
                TTTButton(0, 2, tttViewModel)
            }
            Column() {
                TTTButton(1, 0, tttViewModel)
                TTTButton(1, 1, tttViewModel)
                TTTButton(1, 2, tttViewModel)
            }
            Column() {
                TTTButton(2, 0, tttViewModel)
                TTTButton(2, 1, tttViewModel)
                TTTButton(2, 2, tttViewModel)
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        Text(if (tttViewModel.gameOver) "Game over!" else "Your turn!")
    }
}
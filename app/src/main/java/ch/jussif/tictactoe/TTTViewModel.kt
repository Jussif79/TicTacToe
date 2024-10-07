package ch.jussif.tictactoe

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ch.jussif.tictactoe.ApiService.ApiObject
import kotlinx.coroutines.launch
import org.json.JSONArray
import org.json.JSONObject

class TTTViewModel : ViewModel() {
    var pingResult by mutableStateOf(false)
    var loginToken by mutableStateOf("")
    var gameState by mutableStateOf(Array(3) { Array(3) { 0 } })
    var gameOver by mutableStateOf(false)

    fun ping() {
        viewModelScope.launch {
            val pingString = ApiObject.retrofitService.ping()
            pingResult = pingString.contains("success")
        }
    }

    fun register(userName: String, password: String) {
        viewModelScope.launch {
            val userAndPassword = JSONObject().apply {
                put("userName", userName)
                put("password", password)
            }
            val registerString = ApiObject.retrofitService.register(userAndPassword.toString())
        }
    }

    fun login(userName: String, password: String) {
        viewModelScope.launch {
            val userAndPassword = JSONObject().apply {
                put("userName", userName)
                put("password", password)


            }
            val loginString = ApiObject.retrofitService.login(userAndPassword.toString())
            val loginJSON = JSONObject(loginString)

            try {
                loginToken = loginJSON.getString("token")
            } catch (e: Exception) {
                loginToken = "" // In case of an invalid login
            }
        }
    }

    fun newGame(token: String) {
        viewModelScope.launch {
            val newGame = JSONObject().apply {
                put("token", token)
                put("gameType", "TicTacToe")
                put("difficulty", 2)
                put("options", "")
                put("board", JSONObject.NULL)
                put("result", JSONObject.NULL)
            }
            val gameString = ApiObject.retrofitService.newGame(newGame.toString())
            val gameJSON = JSONObject(gameString)

            updateGameStatus(gameJSON)
        }
    }

    fun makeMove(row: Int, column: Int) {
        // needs implementation
    }

    fun updateGameStatus(json: JSONObject) {
        val gameArray = json.get("board") as JSONArray
        val newGameState = Array(3) { Array(3) { 99 } }

        for (row in 0..2) {
            val rowArray = gameArray[row] as JSONArray
            for (column in 0..2) {
                newGameState[row][column] = rowArray[column] as Int
            }
        }
        gameState = newGameState
        gameOver = json.getBoolean("result")
    }

}
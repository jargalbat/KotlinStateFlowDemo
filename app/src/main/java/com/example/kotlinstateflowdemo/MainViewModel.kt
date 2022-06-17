package com.example.kotlinstateflowdemo

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.net.HttpURLConnection
import java.net.URL

@ExperimentalCoroutinesApi
class MainViewModel : ViewModel() {
    private var _loginUiState = MutableStateFlow<LoginUiState>(LoginUiState.Empty)
    val loginUiState: StateFlow<LoginUiState> = _loginUiState

    fun login(username: String, password: String) = viewModelScope.launch {
        _loginUiState.value = LoginUiState.Loading
//        delay(2000L)
        if (username == "1" && password == "1") {
            _loginUiState.value = LoginUiState.Success("")
        } else {
            _loginUiState.value = LoginUiState.Error("Wrong credentials")
        }
    }

    fun getCookie() {
        GlobalScope.launch(Dispatchers.IO) {
            _loginUiState.value = LoginUiState.Loading

            try {
                val url =
                    URL("https://mongolia-today.com/api/mobile-app/magazine/v1/issues/80/online-view") // Mongolia today
//                    URL("https://staging.wplus.world/api/mobile-app/magazine/v1/issues/1200/online-view") // Olympics
//                    URL("https://staging.wplus.world/api/mobile-app/magazine/v1/issues/776/online-view") // Korea

                with(url.openConnection() as HttpURLConnection) {
                    requestMethod = "POST"  // optional default is GET
                    setRequestProperty("Accept", "application/json")
                    setRequestProperty("Device-UUID", "A358D8BD-69AA-49D6-8FA2-E324E873DD78")
                    println("\nSent 'POST' request to URL : $url; Response Code : $responseCode")
                    if (responseCode == 200) {
                        val data = inputStream.bufferedReader().readText()
                        if (data.isNotEmpty()) {
                            _loginUiState.value = LoginUiState.Success(data)
                        } else {
                            _loginUiState.value = LoginUiState.Error("Data not found")
                        }
                    } else {
                        _loginUiState.value = LoginUiState.Error("Request failed")
                    }

                }

            } catch (e: Exception) {
                Log.w("jagaatest", e.toString())
                _loginUiState.value = LoginUiState.Error("Error occurred")
            }
        }

    }

    sealed class LoginUiState {
        data class Success(val data: String) : LoginUiState()
        data class Error(val message: String) : LoginUiState()
        object Loading : LoginUiState()
        object Empty : LoginUiState()
    }

}
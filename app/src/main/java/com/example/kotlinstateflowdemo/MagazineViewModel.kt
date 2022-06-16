package com.example.kotlinstateflowdemo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MagazineViewModel : ViewModel() {

    private var _magazineUiState = MutableStateFlow<MagazineUiState>(MagazineUiState.Empty)
    val magazineUiState: StateFlow<MagazineUiState> = _magazineUiState

    fun login(username: String, password: String) = viewModelScope.launch {
        _magazineUiState.value = MagazineUiState.Loading
        delay(2000L)
        if (username == "1" && password == "1") {
            _magazineUiState.value = MagazineUiState.Success
        } else {
            _magazineUiState.value = MagazineUiState.Error("Wrong credentials")
        }
    }

    sealed class MagazineUiState {
        object Success : MagazineUiState()
        data class Error(val message: String) : MagazineUiState()
        object Loading : MagazineUiState()
        object Empty : MagazineUiState()
    }
}
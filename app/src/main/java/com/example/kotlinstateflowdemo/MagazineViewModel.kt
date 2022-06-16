package com.example.kotlinstateflowdemo

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.net.HttpURLConnection
import java.net.URL


class MagazineViewModel : ViewModel() {
    private var _magazineUiState = MutableStateFlow<MagazineUiState>(MagazineUiState.Empty)
    val magazineUiState: StateFlow<MagazineUiState> = _magazineUiState

    sealed class MagazineUiState {
        object Success : MagazineUiState()
        data class Error(val message: String) : MagazineUiState()
        object Loading : MagazineUiState()
        object Empty : MagazineUiState()
    }
}
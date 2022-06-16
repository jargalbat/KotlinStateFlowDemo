package com.example.kotlinstateflowdemo

import android.util.Log
import android.webkit.CookieManager
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.net.HttpURLConnection
import java.net.URL


class MagazineViewModel : ViewModel() {
    private var _magazineUiState = MutableStateFlow<MagazineUiState>(MagazineUiState.Empty)
    val magazineUiState: StateFlow<MagazineUiState> = _magazineUiState

    fun setCookie(jsonData: JsonData) {
        // Cookie
        val cookieManager = CookieManager.getInstance()
        cookieManager.setAcceptCookie(true)
//        cookieManager.setAcceptThirdPartyCookies(webView, true)
        cookieManager.removeAllCookies(null)
        for (el in jsonData.data.cookie) {
            cookieManager.setCookie(
                el.domain,
                "${el.key}=${el.value};" + "path=${el.path};"
            )
        }
        cookieManager.flush()
    }

    fun setUserInputEnabled(isUserInputEnabled: Boolean) {
        _magazineUiState.value = MagazineUiState.SetUserInputEnabled(isUserInputEnabled)
    }

    sealed class MagazineUiState {
        object Success : MagazineUiState()
        data class Error(val message: String) : MagazineUiState()
        data class SetUserInputEnabled(val isUserInputEnabled: Boolean) : MagazineUiState()
        object Loading : MagazineUiState()
        object Empty : MagazineUiState()
    }
}
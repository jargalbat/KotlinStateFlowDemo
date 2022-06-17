package com.example.kotlinstateflowdemo

import android.webkit.CookieManager
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

//import android.util.Log
//import android.webkit.CookieManager
//import androidx.lifecycle.ViewModel
//import kotlinx.coroutines.flow.MutableStateFlow
//import kotlinx.coroutines.flow.StateFlow
//import java.net.HttpURLConnection
//import java.net.URL


class IssueViewModel : ViewModel() {
    private var _issueUiState = MutableStateFlow<IssueUiState>(IssueUiState.Empty)
    val issueUiState: StateFlow<IssueUiState> = _issueUiState

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
        _issueUiState.value = IssueUiState.SetUserInputEnabled(isUserInputEnabled)
    }

    sealed class IssueUiState {
        object Success : IssueUiState()
        data class Error(val message: String) : IssueUiState()
        data class SetUserInputEnabled(val isUserInputEnabled: Boolean) : IssueUiState()
        object Loading : IssueUiState()
        object Empty : IssueUiState()
    }

}
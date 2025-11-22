package com.example.meet4learn.ui.viewmodels

data class LoginUiState(
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val isStudent: Boolean = false,
    val errorMessage: String? = null
)
package com.example.meet4learn.ui.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.meet4learn.domain.repositories.AuthRepository
import com.example.meet4learn.domain.usecase.LoginStudentUseCase
import kotlinx.coroutines.launch

class LoginViewModel(private val loginStudentUseCase: LoginStudentUseCase) : ViewModel() {
    var uiState by mutableStateOf(LoginUiState())
        private set

    fun loginUser(email: String, password: String) {
        viewModelScope.launch {
            uiState = uiState.copy(isLoading = true, errorMessage = null)

            val result = loginStudentUseCase(email, password)

            result.fold(
                onSuccess = {
                    uiState = uiState.copy(
                        isLoading = false,
                        isSuccess = true,
                        errorMessage = null
                    )
                },
                onFailure = { error ->
                    uiState = uiState.copy(
                        isLoading = false,
                        isSuccess = false,
                        //Mensaje del UseCase.
                        errorMessage = error.message ?: "Error desconocido."
                    )
                }
            )
        }
    }
    fun resetState() {
        uiState = LoginUiState()
    }
}
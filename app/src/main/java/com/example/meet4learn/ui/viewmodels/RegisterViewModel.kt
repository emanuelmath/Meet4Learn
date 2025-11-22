package com.example.meet4learn.ui.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.meet4learn.domain.usecase.RegisterStudentUseCase
import kotlinx.coroutines.launch

class RegisterViewModel(private val registerStudentUseCase: RegisterStudentUseCase) : ViewModel() {

    var uiState by mutableStateOf(RegisterUiState())
        private set

    fun register(email: String, password: String, fullName: String) {
        viewModelScope.launch {
            uiState = uiState.copy(isLoading = true, errorMessage = null)

            val result = registerStudentUseCase(email, password, fullName)

            result.fold(
                onSuccess = {
                    uiState = uiState.copy(
                        isLoading = false,
                        isSuccess = true,
                        errorMessage = null
                    )
                },
                onFailure = {
                        error ->
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

    fun sendErrorMessage(error: String) {
        uiState = uiState.copy(
            isLoading = false,
            isSuccess = false,
            errorMessage = error
        )
    }

}
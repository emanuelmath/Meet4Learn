package com.example.meet4learn.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.meet4learn.domain.usecase.LoginStudentUseCase

class LoginViewModelFactory (
    private val loginStudentUseCase: LoginStudentUseCase
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(loginStudentUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
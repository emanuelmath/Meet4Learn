package com.example.meet4learn.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.meet4learn.domain.usecase.LoginStudentUseCase
import com.example.meet4learn.domain.usecase.RegisterStudentUseCase

class RegisterViewModelFactory (
    private val registerStudentUseCase: RegisterStudentUseCase
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RegisterViewModel::class.java)) {
            return RegisterViewModel(registerStudentUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
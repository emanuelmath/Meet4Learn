package com.example.meet4learn.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.meet4learn.domain.repositories.AuthRepository
import kotlinx.coroutines.launch

class SplashViewModel(private val authRepository: AuthRepository) : ViewModel() {
    fun isUserLoggedIn(): Boolean {
        var response: Boolean = false
        viewModelScope.launch {
           response = authRepository.isUserLoggedIn()
        }
        return response
    }
}
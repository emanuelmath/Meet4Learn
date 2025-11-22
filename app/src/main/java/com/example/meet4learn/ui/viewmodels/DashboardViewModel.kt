package com.example.meet4learn.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.meet4learn.domain.repositories.AuthRepository
import kotlinx.coroutines.launch

class DashboardViewModel(private val authRepository: AuthRepository): ViewModel() {
    fun logOut() {
        viewModelScope.launch {
            authRepository.logout()
        }
    }
}
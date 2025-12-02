package com.example.meet4learn.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.meet4learn.domain.repositories.AuthRepository
import com.example.meet4learn.domain.repositories.ProfileRepository
import com.example.meet4learn.domain.repositories.SavedPaymentMethodRepository

class StudentProfileViewModelFactory (
    private val savedPaymentMethodRepository: SavedPaymentMethodRepository,
    private val profileRepository: ProfileRepository, private val authRepository: AuthRepository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(StudentProfileViewModel::class.java)) {
            return StudentProfileViewModel(savedPaymentMethodRepository, profileRepository, authRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
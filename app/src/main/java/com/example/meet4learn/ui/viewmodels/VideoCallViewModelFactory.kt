package com.example.meet4learn.ui.viewmodels

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.meet4learn.domain.repositories.AuthRepository
import com.example.meet4learn.domain.repositories.CallRepository
import com.example.meet4learn.domain.repositories.ChatMessageRepository
import com.example.meet4learn.domain.repositories.ProfileRepository
import com.example.meet4learn.domain.repositories.SavedPaymentMethodRepository

class VideoCallViewModelFactory (
    private val application: Application,
    private val chatRepository: ChatMessageRepository,
    private val callRepository: CallRepository,
    private val authRepository: AuthRepository,
    private val profileRepository: ProfileRepository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(VideoCallViewModel::class.java)) {
            return VideoCallViewModel(
                application,
                chatRepository,
                callRepository,
                authRepository,
                profileRepository
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
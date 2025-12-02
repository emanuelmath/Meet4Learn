package com.example.meet4learn.ui.viewmodels

import com.example.meet4learn.domain.models.Profile
import com.example.meet4learn.domain.models.SavedPaymentMethod

data class StudentProfileUiState(
    val isLoading: Boolean = true,
    val profileStudent: Profile? = null,
    val email: String = "",
    val cards: List<SavedPaymentMethod> = emptyList(),
    val isRefresh: Boolean = false,
    val isAddCardDialogOpen: Boolean = false,
    val newCardName: String = "",
    val newCardNumber: String = "",
    val newCardExpiry: String = "",
    val newCardCvv: String = "",
    val errorMessage: String? = null,
    val successMessage: String? = null

)

package com.example.meet4learn.domain.models

data class SavedPaymentMethod(
    val id: Int,
    val userId: String,
    val lastFourDigits: String,
    val brand: String,
    val expirationMonth: Int,
    val expirationYear: Int
)

package com.example.meet4learn.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class SavedPaymentMethodDTO(
    val id: Int? = null,
    val user_id: String,
    val last_four_digits: String,
    val brand: String,
    val expiration_month: Int,
    val expiration_year: Int
)

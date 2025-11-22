package com.example.meet4learn.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class ProfileDTO(
    val id: String,
    val full_name: String,
    val role: String,
    val balance: Double
)
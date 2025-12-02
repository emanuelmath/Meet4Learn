package com.example.meet4learn.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class LiveKitTokenResponse(
    val token: String
)
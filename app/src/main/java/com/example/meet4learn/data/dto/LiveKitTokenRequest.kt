package com.example.meet4learn.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class LiveKitTokenRequest(
    val roomName: String,
    val participantName: String
)
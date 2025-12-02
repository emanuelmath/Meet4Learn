package com.example.meet4learn.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class ChatMessageDTO(
    val id: Int? = null,
    val moduleId: Int,
    val senderId: String,
    val messageText: String,
    val time: String
)

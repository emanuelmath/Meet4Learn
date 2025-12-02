package com.example.meet4learn.domain.models

import java.time.OffsetDateTime

data class ChatMessage(
    val id: Int,
    val moduleId: Int,
    val senderId: String,
    val messageText: String,
    val time: OffsetDateTime
)
package com.example.meet4learn.data.mappers

import com.example.meet4learn.data.dto.ChatMessageDTO
import com.example.meet4learn.domain.models.ChatMessage
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter

private val formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME

fun ChatMessageDTO.toModel(): ChatMessage {
    return ChatMessage (
        id = id ?: 0,
        moduleId = moduleId,
        senderId = senderId,
        messageText = messageText,
        time = OffsetDateTime.parse(time)
    )
}

fun ChatMessage.toDTO(): ChatMessageDTO {
    return ChatMessageDTO(
        id = id,
        moduleId = moduleId,
        senderId = senderId,
        messageText = messageText,
        time = time.format(formatter)
    )
}
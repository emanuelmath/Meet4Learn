package com.example.meet4learn.domain.repositories

import com.example.meet4learn.domain.models.ChatMessage
import kotlinx.coroutines.flow.Flow

interface ChatMessageRepository {
    fun getMessagesRealtime(moduleId: Int): Flow<List<ChatMessage>>
    suspend fun sendMessage(moduleId: Int, userId: String, text: String)
}
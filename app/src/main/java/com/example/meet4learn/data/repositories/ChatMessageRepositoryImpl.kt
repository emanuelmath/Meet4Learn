package com.example.meet4learn.data.repositories

import com.example.meet4learn.data.dto.ChatMessageDTO
import com.example.meet4learn.data.mappers.toModel
import com.example.meet4learn.domain.models.ChatMessage
import com.example.meet4learn.domain.repositories.ChatMessageRepository
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.postgrest.query.filter.FilterOperation
import io.github.jan.supabase.postgrest.query.filter.FilterOperator
import io.github.jan.supabase.realtime.selectAsFlow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import io.github.jan.supabase.annotations.SupabaseExperimental
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter

@OptIn(SupabaseExperimental::class)
class ChatMessageRepositoryImpl(private val supabaseClient: SupabaseClient) : ChatMessageRepository {
    override fun getMessagesRealtime(moduleId: Int): Flow<List<ChatMessage>> {
        return supabaseClient.from("chat_messages")
            .selectAsFlow(ChatMessageDTO::id)
            .map { list ->
                list
                    .filter { it.moduleId == moduleId }
                    .map { it.toModel() }
                    .sortedBy { it.time }
            }
//        return supabaseClient.from("chat_messages")
//            .selectAsFlow(
//                primaryKey = ChatMessageDTO::id,
//                filter = FilterOperation("moduleId", FilterOperator.EQ, moduleId),
//            )
//            .map { listDTO ->
//                listDTO
//                    .filter { it.moduleId == moduleId }
//                    .sortedBy { it.time }
//                    .map { it.toModel() }
//            }
    }

    override suspend fun sendMessage(
        moduleId: Int,
        userId: String,
        text: String
    ) {
        val dto = ChatMessageDTO(
            moduleId = moduleId,
            senderId = userId,
            messageText = text,
            time = OffsetDateTime.now().format(DateTimeFormatter.ISO_OFFSET_DATE_TIME)
        )
        supabaseClient.from("chat_messages").insert(dto)
    }
}
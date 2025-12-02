package com.example.meet4learn.data.repositories

import com.example.meet4learn.data.dto.LiveKitTokenRequest
import com.example.meet4learn.data.dto.LiveKitTokenResponse
import com.example.meet4learn.domain.repositories.CallRepository
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.functions.functions
import io.ktor.client.statement.bodyAsText
import kotlinx.serialization.json.Json

class CallRepositoryImpl(private val supabaseClient: SupabaseClient) : CallRepository {
    override suspend fun joinSession(roomName: String, userId: String, isTeacher: Boolean): Result<String> {
        return try {
            val response = supabaseClient.functions.invoke(
                function = "get-livekit-token",
                body = LiveKitTokenRequest(
                    roomName = roomName,
                    participantName = userId
                )
            )

            val responseString = response.bodyAsText()

            val jsonConfig = Json { ignoreUnknownKeys = true }
            val data = jsonConfig.decodeFromString<LiveKitTokenResponse>(responseString)

            Result.success(data.token)

        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure(e)
        }
    }
}
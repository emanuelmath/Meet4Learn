package com.example.meet4learn.ui.viewmodels

import android.app.Application
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.meet4learn.domain.models.ChatMessage
import com.example.meet4learn.domain.models.Profile
import com.example.meet4learn.domain.repositories.AuthRepository
import com.example.meet4learn.domain.repositories.CallRepository
import com.example.meet4learn.domain.repositories.ChatMessageRepository
import com.example.meet4learn.domain.repositories.ProfileRepository
import io.livekit.android.LiveKit
import io.livekit.android.events.RoomEvent
import io.livekit.android.events.collect
import io.livekit.android.renderer.TextureViewRenderer
import io.livekit.android.room.Room
import io.livekit.android.room.track.VideoTrack
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class VideoCallViewModel(
    application: Application,
    private val chatRepository: ChatMessageRepository,
    private val callRepository: CallRepository,
    private val authRepository: AuthRepository,
    private val profileRepository: ProfileRepository
) : AndroidViewModel(application) {

    var uiState by mutableStateOf(VideoCallUiState())
        private set

    private val room: Room = LiveKit.create(getApplication())

    fun initVideoRenderer(renderer: TextureViewRenderer) {
        room.initVideoRenderer(renderer)
    }
    fun initSession(moduleId: Int) {
        viewModelScope.launch {
            uiState = uiState.copy(isLoading = true)
            val userId = authRepository.getCurrentUserId() ?: ""
            uiState = uiState.copy(currentUserId = userId)

            resolveProfile(userId)

            launch { subscribeToChat(moduleId) }

            launch { collectRoomEvents() }

            launch { connectToLiveKit(moduleId.toString(), userId) }
        }
    }

    private suspend fun collectRoomEvents() {
        room.events.collect { event ->
            when (event) {
                is RoomEvent.TrackSubscribed -> {
                    val track = event.track
                    val publication = event.publication
                    Log.d("VideoDebug", "Track suscrito. Tipo: ${track.kind.name}, Nombre: ${track.name}")

                    if (track is VideoTrack) {
                        Log.d("VideoDebug", "¡ES VIDEO! Asignando a UI State...")
                        uiState = uiState.copy(teacherVideoTrack = track)
                    }
                }
                is RoomEvent.TrackSubscriptionFailed -> {
                    Log.e("VideoDebug", "Fallo suscripción: ${event.exception.message}")
                }
                else -> {}
            }
        }
    }

    private suspend fun connectToLiveKit(roomName: String, userId: String) {
        val result = callRepository.joinSession(roomName, userId, isTeacher = false)

        result.onSuccess { token ->
            try {
                val wsUrl = "wss://meet4learn-d3h5oi4q.livekit.cloud"
                room.connect(wsUrl, token)
                uiState = uiState.copy(isConnected = true, isLoading = false)
            } catch (e: Exception) {
                uiState = uiState.copy(isLoading = false, errorMessage = "Error conectando video: ${e.message}")
            }
        }.onFailure { e ->
            uiState = uiState.copy(isLoading = false, errorMessage = "No se pudo obtener acceso: ${e.message}")
        }
    }

    private suspend fun subscribeToChat(moduleId: Int) {
        chatRepository.getMessagesRealtime(moduleId)
            .catch { e ->
                Log.e("VideoVM", "Error chat: ${e.message}")
                uiState = uiState.copy(errorMessage = "Error cargando chat")
            }
            .collect { newList ->
                uiState = uiState.copy(messages = newList)
                resolveMissingProfiles(newList)
            }
    }

    private fun resolveMissingProfiles(messages: List<ChatMessage>) {
        viewModelScope.launch {
            val senderIds = messages.map { it.senderId }.distinct()
            val missingIds = senderIds.filter { !uiState.userProfiles.containsKey(it) }

            if (missingIds.isNotEmpty()) {
                val newProfiles = mutableMapOf<String, Profile>()
                missingIds.forEach { id ->
                    resolveProfile(id)?.let { profile ->
                        newProfiles[id] = profile
                    }
                }
                uiState = uiState.copy(userProfiles = uiState.userProfiles + newProfiles)
            }
        }
    }

    private suspend fun resolveProfile(userId: String): Profile? {
        return try {
            profileRepository.getStudentById(userId)
        } catch (e: Exception) {
            null
        }
    }

    fun sendMessage(moduleId: Int, text: String) {
        viewModelScope.launch {
            try {
                if (text.isNotBlank()) {
                    chatRepository.sendMessage(moduleId, uiState.currentUserId, text)
                }
            } catch (e: Exception) {
                Log.e("VideoVM", "Error enviando: ${e.message}")
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        room.disconnect()
        room.release()
    }
}
package com.example.meet4learn.ui.viewmodels

import com.example.meet4learn.domain.models.ChatMessage
import com.example.meet4learn.domain.models.Profile
import io.livekit.android.room.track.VideoTrack

data class VideoCallUiState(
    val messages: List<ChatMessage> = emptyList(),
    val currentUserId: String = "",
    val userProfiles: Map<String, Profile> = emptyMap(),
    val teacherVideoTrack: VideoTrack? = null,
    val isConnected: Boolean = false,
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)
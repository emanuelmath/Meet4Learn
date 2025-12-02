package com.example.meet4learn.domain.repositories

interface CallRepository {
    suspend fun joinSession(roomName: String, userId: String, isTeacher: Boolean): Result<String>
}
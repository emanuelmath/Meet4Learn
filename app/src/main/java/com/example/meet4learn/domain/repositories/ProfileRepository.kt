package com.example.meet4learn.domain.repositories

interface ProfileRepository {
    suspend fun getUserRole(userId: String): Result<String>
}
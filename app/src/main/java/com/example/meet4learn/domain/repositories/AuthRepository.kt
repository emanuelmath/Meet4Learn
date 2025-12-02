package com.example.meet4learn.domain.repositories

interface AuthRepository {
    suspend fun login(email: String, pass: String): Result<String>
    suspend fun logout()
    suspend fun isUserLoggedIn(): Boolean
    fun getCurrentUserId(): String?
    fun getCurrentUserEmail(): String?
    suspend fun signUp(
        email: String,
        pass: String,
        fullName: String
    ): Result<String>

}
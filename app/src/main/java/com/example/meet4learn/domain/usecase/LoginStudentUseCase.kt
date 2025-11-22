package com.example.meet4learn.domain.usecase

import com.example.meet4learn.domain.repositories.AuthRepository
import com.example.meet4learn.domain.repositories.ProfileRepository

class LoginStudentUseCase(
    private val authRepository: AuthRepository,
    private val profileRepository: ProfileRepository
) {
    suspend operator fun invoke(email: String, pass: String): Result<Unit> {
        val loginResult = authRepository.login(email, pass)

        if (loginResult.isFailure) {
            return Result.failure(loginResult.exceptionOrNull()!!)
        }

        val userId = loginResult.getOrThrow()

        val roleResult = profileRepository.getUserRole(userId)

        if (roleResult.isFailure) {
            authRepository.logout()
            return Result.failure(Exception("No se pudo verificar tu perfil."))
        }

        val role = roleResult.getOrThrow()

        if (role.equals("student", ignoreCase = true)) {
            return Result.success(Unit)
        } else {
            authRepository.logout()
            return Result.failure(Exception("¡Eres parte de la familia Meet4Learn! Pero esta app es solo para estudiantes, ingresa al sitio web."))
        }
    }
}
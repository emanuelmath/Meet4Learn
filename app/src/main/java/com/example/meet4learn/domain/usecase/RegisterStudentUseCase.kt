package com.example.meet4learn.domain.usecase

import com.example.meet4learn.domain.repositories.AuthRepository

class RegisterStudentUseCase (private val authRepository: AuthRepository) {
        suspend operator fun invoke(
            email: String,
            password: String,
            fullName: String
        ): Result<Unit> {

           // Validar contraseña en longitud y caracteres especiales.
            if (password.length < 8) {
                return Result.failure(Exception("La contraseña debe tener mínimo 8 caracteres."))
            }
            else if(!password.contains('#') && !password.contains('$')) {
                return Result.failure(Exception("Ingresa # o $ para una contraseña más segura."))
            }

            val result = authRepository.signUp(email, password, fullName)

            return if (result.isSuccess) Result.success(Unit)
            else Result.failure(result.exceptionOrNull()!!)
        }
}


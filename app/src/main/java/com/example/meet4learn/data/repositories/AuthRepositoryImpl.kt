package com.example.meet4learn.data.repositories

import com.example.meet4learn.data.dto.ProfileDTO
import com.example.meet4learn.domain.repositories.AuthRepository
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.auth.providers.builtin.Email
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.postgrest.query.Columns
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put

class AuthRepositoryImpl( private val supabaseClient: SupabaseClient ) : AuthRepository {
    override suspend fun login(email: String, pass: String): Result<String> {
        return try {
            supabaseClient.auth.signInWith(Email) {
                this.email = email
                this.password = pass
            }
            // Si no lanza error, se retorna el ID del usuario logueado.
            val userId = supabaseClient.auth.currentUserOrNull()?.id
                ?: throw Exception("Error obteniendo ID de usuario.")
            Result.success(userId)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun logout() {
        supabaseClient.auth.signOut()
    }

    override suspend fun isUserLoggedIn(): Boolean {
        return supabaseClient.auth.currentSessionOrNull() != null
    }

    override fun getCurrentUserId(): String? {
        return supabaseClient.auth.currentUserOrNull()?.id
    }

    override suspend fun signUp(email: String, password: String, fullName: String): Result<String> {
        return try {
            val user = supabaseClient.auth.signUpWith(Email) {
                this.email = email
                this.password = password

                data = buildJsonObject {
                    put("role", "student")
                    put("full_name", fullName)
                }
            }

            val userId = user?.id ?: throw Exception("Error sin ID.")
            Result.success(userId)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override fun getCurrentUserEmail(): String? {
        return supabaseClient.auth.currentUserOrNull()?.email
    }

}
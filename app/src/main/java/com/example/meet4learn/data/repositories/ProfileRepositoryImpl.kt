package com.example.meet4learn.data.repositories

import android.util.Log
import com.example.meet4learn.data.dto.ProfileDTO
import com.example.meet4learn.data.mappers.toModel
import com.example.meet4learn.domain.models.Profile
import com.example.meet4learn.domain.repositories.ProfileRepository
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.postgrest.query.Columns
import kotlinx.serialization.json.put
import kotlinx.serialization.json.buildJsonObject


class ProfileRepositoryImpl( val supabaseClient: SupabaseClient) : ProfileRepository {
    override suspend fun getUserRole(userId: String): Result<String> {
        return try {
            val resultDTO = supabaseClient.from("profile")
                .select(columns = Columns.ALL /*Columns.list("role")*/) {
                    filter {
                        eq("id", userId)
                    }
                }
                .decodeSingle<ProfileDTO>()

            val resultModel = resultDTO.toModel()

            Result.success(resultModel.role)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getTeacherName(teacherId: String): Result<String> {
        return try {
            val resultDTO = supabaseClient.from("profile")
                .select(columns = Columns.ALL) {
                    filter {
                        eq("id", teacherId)
                    }
                }
                .decodeSingle<ProfileDTO>()

            val resultModel = resultDTO.toModel()

            Result.success(resultModel.fullName)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getStudentBalance(studentId: String): Result<Double> {
        return try {
            val resultDTO = supabaseClient.from("profile").select {
                filter {
                    eq("id", studentId)
                }
            }.decodeSingle<ProfileDTO>()

            val resultModel = resultDTO.toModel()

            Result.success(resultModel.balance)
        } catch(e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getStudentById(studentId: String): Profile? {
        return try {
                supabaseClient.from("profile").select {
                filter {
                    eq("id", studentId)
                }
            }.decodeSingle<ProfileDTO>().toModel()

        } catch(e: Exception) {
            Log.d("getStudentById", e.message ?: "Error desconocido.")
            null
        }
    }

    override suspend fun updateBalance(amount: Double, studentId: String) {
        supabaseClient.from("profile").update(
            buildJsonObject {
                put("balance", amount)
            }
        ) {
            filter {
                eq("id", studentId)
            }
        }
    }
}
package com.example.meet4learn.data.repositories

import com.example.meet4learn.data.dto.ProfileDTO
import com.example.meet4learn.data.mappers.toModel
import com.example.meet4learn.domain.repositories.ProfileRepository
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.postgrest.query.Columns

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

}
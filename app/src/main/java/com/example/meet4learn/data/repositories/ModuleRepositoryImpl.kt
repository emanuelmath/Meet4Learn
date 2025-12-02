package com.example.meet4learn.data.repositories

import com.example.meet4learn.data.dto.ModuleDTO
import com.example.meet4learn.data.mappers.toModel
import com.example.meet4learn.domain.models.Module
import com.example.meet4learn.domain.repositories.ModuleRepository
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.postgrest.query.Columns

class ModuleRepositoryImpl( private val supabaseClient: SupabaseClient ) : ModuleRepository {
    override suspend fun getModuleByID() {
        TODO("Not yet implemented")
    }

    override suspend fun getModulesByCourseId(courseId: Int): List<Module> {
        return supabaseClient.from("modules")
            .select(Columns.ALL) {
                filter {
                    eq("courseId",courseId)
                }
            }.decodeList<ModuleDTO>().map { it.toModel() }
    }
}
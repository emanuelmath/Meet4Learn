package com.example.meet4learn.data.repositories

import com.example.meet4learn.data.dto.CourseDTO
import com.example.meet4learn.data.mappers.toModel
import io.github.jan.supabase.realtime.selectAsFlow
import com.example.meet4learn.domain.models.Course
import com.example.meet4learn.domain.repositories.CourseRepository
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.from
import kotlinx.coroutines.flow.Flow
import com.example.meet4learn.ui.viewmodels.*
import io.github.jan.supabase.annotations.SupabaseExperimental
import io.github.jan.supabase.postgrest.query.Columns
import kotlinx.coroutines.flow.map

@OptIn(SupabaseExperimental::class)
class CourseRepositoryImpl( val supabaseClient: SupabaseClient ) : CourseRepository {
    override fun getAllCoursesRealtime(): Flow<List<Course>> {
        return supabaseClient.from("course")
            .selectAsFlow(
                primaryKey = CourseDTO::id
            )
            .map { listDTO ->
                listDTO
                    .map { it.toModel() }
                    .filter { it.status == "activo" }
            }
    }

    override suspend fun getCourseById(id: Int): Course? {
        return supabaseClient.from("course")
            .select(columns = Columns.ALL) {
            filter { eq("id", id) }
        }.decodeSingleOrNull<CourseDTO>()?.toModel()
    }

    override suspend fun getCoursesByIds(courseIds: List<Int>): List<Course> {
        if (courseIds.isEmpty()) return emptyList()

        return supabaseClient.from("course")
            .select(columns = Columns.ALL) {
                filter {
                    isIn("id", courseIds)
                }
            }.decodeList<CourseDTO>().map { it.toModel() }
    }
}
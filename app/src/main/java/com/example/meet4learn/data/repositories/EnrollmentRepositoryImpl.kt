package com.example.meet4learn.data.repositories

import com.example.meet4learn.data.dto.EnrollmentDTO
import com.example.meet4learn.data.mappers.toModel
import com.example.meet4learn.domain.models.Enrollment
import com.example.meet4learn.domain.repositories.EnrollmentRepository
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.postgrest.query.Columns

class EnrollmentRepositoryImpl( private val supabaseClient: SupabaseClient ) : EnrollmentRepository {
    override suspend fun getMyEnrollments(studentId: String): List<Enrollment> {
        return supabaseClient.from("enrollments")
            .select(columns = Columns.ALL) {
                filter {
                    eq("studentId", studentId)
                }
            }.decodeList<EnrollmentDTO>().map { it.toModel() }
    }

    override suspend fun enrollStudent(userId: String, courseId: Int) {
        TODO("Not yet implemented")
    }
}
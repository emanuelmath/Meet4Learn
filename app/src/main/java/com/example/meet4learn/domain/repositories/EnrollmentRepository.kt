package com.example.meet4learn.domain.repositories

import com.example.meet4learn.data.dto.EnrollmentDTO
import com.example.meet4learn.domain.models.Enrollment

interface EnrollmentRepository {
    suspend fun getMyEnrollments(studentId: String): List<Enrollment>
    suspend fun enrollStudent(userId: String, courseId: Int)
}

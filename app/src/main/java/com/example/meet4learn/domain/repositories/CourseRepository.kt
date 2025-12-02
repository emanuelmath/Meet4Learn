package com.example.meet4learn.domain.repositories

import com.example.meet4learn.data.dto.CourseDTO
import com.example.meet4learn.domain.models.Course
import kotlinx.coroutines.flow.Flow

interface CourseRepository {
    fun getAllCoursesRealtime(): Flow<List<Course>>
    suspend fun getCourseById(id: Int): Course?
    // suspend fun getMyCourses(id: String) No -> Es para enrollments
}
package com.example.meet4learn.domain.repositories

interface ProfileRepository {
    suspend fun getUserRole(userId: String): Result<String>
    suspend fun getTeacherName(teacherId: String): Result<String>
    suspend fun getStudentBalance(studentId: String): Result<Double>
}
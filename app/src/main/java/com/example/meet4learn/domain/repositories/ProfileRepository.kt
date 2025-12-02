package com.example.meet4learn.domain.repositories

import com.example.meet4learn.domain.models.Profile

interface ProfileRepository {
    suspend fun getUserRole(userId: String): Result<String>
    suspend fun getTeacherName(teacherId: String): Result<String>
    suspend fun getStudentBalance(studentId: String): Result<Double>
    suspend fun getStudentById(studentId: String): Profile?
    suspend fun updateBalance(amount: Double, studentId: String)
}
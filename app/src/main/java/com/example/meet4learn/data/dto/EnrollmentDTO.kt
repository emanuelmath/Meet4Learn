package com.example.meet4learn.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class EnrollmentDTO(
    val studentId: String,
    val courseId: Int
)
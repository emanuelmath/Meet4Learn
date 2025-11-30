package com.example.meet4learn.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class CourseDTO(
    val id: Int,
    val name: String,
    val category: String,
    val description: String?,
    val start_date: String,
    val finish_date: String,
    val price: Double,
    val teacher_id: String,
    val status: String
)
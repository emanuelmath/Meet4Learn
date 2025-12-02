package com.example.meet4learn.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class ModuleDTO(
    val id: Int,
    val courseId: Int,
    val title: String,
    val scheduled_at: String,
    val description: String,
    val status: String
)

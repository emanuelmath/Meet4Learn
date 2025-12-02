package com.example.meet4learn.domain.models

import java.time.OffsetDateTime

data class Module(
    val id: Int,
    val courseId: Int,
    val title: String,
    val scheduledAt: OffsetDateTime,
    val description: String,
    val status: String
)
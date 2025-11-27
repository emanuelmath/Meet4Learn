package com.example.meet4learn.domain.models

import java.util.Date

data class Course(
    val id: Int,
    val name: String,
    val subject: String,
    val startDate: Date,
    val finishDate: Date,
    val price: Double,
    val teacherId: String,
    val status: String
)

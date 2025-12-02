package com.example.meet4learn.ui.utils

import com.example.meet4learn.R

data class CourseUI(
    val id: Int,
    val name: String,
    val description: String,
    val price: String,
    val docentName: String,
    val docentImage: Int = R.drawable.libro_abierto
)
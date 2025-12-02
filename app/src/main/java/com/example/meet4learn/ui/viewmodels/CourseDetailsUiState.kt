package com.example.meet4learn.ui.viewmodels

import com.example.meet4learn.domain.models.Module
import com.example.meet4learn.ui.utils.CourseUI

data class CourseDetailsUiState(
    val isLoading: Boolean = false,
    val course: CourseUI? = null,
    val modules: List<Module> = emptyList(),
    val errorMessage: String? = null,
    val isEnrolled: Boolean = false,
    val enrollmentSuccess: Boolean = false
)

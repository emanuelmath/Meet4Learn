package com.example.meet4learn.ui.viewmodels

import com.example.meet4learn.domain.models.Course

data class DashboardUiState(
    val isLoading: Boolean = true,
    val courses: List<Course> = emptyList(),
    val errorMessage: String? = null
)
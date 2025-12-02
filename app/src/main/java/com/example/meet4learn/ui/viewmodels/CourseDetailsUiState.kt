package com.example.meet4learn.ui.viewmodels

import com.example.meet4learn.domain.models.Module
import com.example.meet4learn.ui.utils.CourseUI

data class CourseDetailsUiState(
    val course: CourseUI? = null,
    val modules: List<Module> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val selectedModule: Module? = null,
    val isDialogOpen: Boolean = false
)

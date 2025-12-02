package com.example.meet4learn.ui.viewmodels

import com.example.meet4learn.domain.models.Module
import com.example.meet4learn.ui.utils.CourseUI

data class CourseDescriptionUiState
    (val course: CourseUI? = null,
     val isLoading: Boolean = false,
     val isEnrolled: Boolean = false,
     val enrollmentSuccess: Boolean = false,
     val errorMessage: String? = null,
     val successMessage: String? = null)

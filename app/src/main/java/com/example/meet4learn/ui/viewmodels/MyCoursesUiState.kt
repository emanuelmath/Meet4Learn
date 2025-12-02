package com.example.meet4learn.ui.viewmodels

import com.example.meet4learn.domain.models.Course
import com.example.meet4learn.domain.models.Enrollment

data class MyCoursesUiState(
    val isLoading: Boolean = true,
    val isRefreshing: Boolean = false,
    val myCourses: List<Course> = emptyList(),
    val myEnrollments: List<Enrollment> = emptyList(),
    val teacherNames: Map<String, String> = emptyMap()
)

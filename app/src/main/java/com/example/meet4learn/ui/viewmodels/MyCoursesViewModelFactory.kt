package com.example.meet4learn.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.meet4learn.domain.repositories.AuthRepository
import com.example.meet4learn.domain.repositories.CourseRepository
import com.example.meet4learn.domain.repositories.EnrollmentRepository
import com.example.meet4learn.domain.repositories.ProfileRepository
import com.example.meet4learn.domain.usecase.LoginStudentUseCase

class MyCoursesViewModelFactory (
    private val courseRepository: CourseRepository,
    private val enrollmentRepository: EnrollmentRepository,
    private val authRepository: AuthRepository,
    private val profileRepository: ProfileRepository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MyCoursesViewModel::class.java)) {
            return MyCoursesViewModel(courseRepository, enrollmentRepository, authRepository, profileRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
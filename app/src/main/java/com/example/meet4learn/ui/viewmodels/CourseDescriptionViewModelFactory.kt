package com.example.meet4learn.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.meet4learn.domain.repositories.AuthRepository
import com.example.meet4learn.domain.repositories.CourseRepository
import com.example.meet4learn.domain.repositories.EnrollmentRepository
import com.example.meet4learn.domain.repositories.ModuleRepository
import com.example.meet4learn.domain.repositories.ProfileRepository

class CourseDescriptionViewModelFactory (
    private val courseRepository: CourseRepository,
    private val profileRepository: ProfileRepository,
    private val enrollmentRepository: EnrollmentRepository,
    private val authRepository: AuthRepository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CourseDescriptionViewModel::class.java)) {
            return CourseDescriptionViewModel(
                courseRepository,
                profileRepository,
                enrollmentRepository,
                authRepository
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
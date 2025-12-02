package com.example.meet4learn.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.meet4learn.domain.repositories.AuthRepository
import com.example.meet4learn.domain.repositories.CourseRepository
import com.example.meet4learn.domain.repositories.EnrollmentRepository
import com.example.meet4learn.domain.repositories.ModuleRepository
import com.example.meet4learn.domain.repositories.ProfileRepository

class CourseDetailsViewModelFactory (
    private val courseRepository: CourseRepository,
    private val profileRepository: ProfileRepository,
    private val enrollmentRepository: EnrollmentRepository,
    private val authRepository: AuthRepository,
    private val moduleRepository: ModuleRepository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CourseDetailsViewModel::class.java)) {
            return CourseDetailsViewModel(
                courseRepository,
                profileRepository,
                enrollmentRepository,
                authRepository,
                moduleRepository
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

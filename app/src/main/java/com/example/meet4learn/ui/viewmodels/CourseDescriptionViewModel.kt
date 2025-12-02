package com.example.meet4learn.ui.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.meet4learn.domain.repositories.AuthRepository
import com.example.meet4learn.domain.repositories.CourseRepository
import com.example.meet4learn.domain.repositories.EnrollmentRepository
import com.example.meet4learn.domain.repositories.ProfileRepository
import com.example.meet4learn.ui.utils.CourseUI
import kotlinx.coroutines.launch

class CourseDescriptionViewModel(
    private val courseRepository: CourseRepository,
    private val profileRepository: ProfileRepository,
    private val enrollmentRepository: EnrollmentRepository,
    private val authRepository: AuthRepository
) : ViewModel() {

    var uiState by mutableStateOf(CourseDescriptionUiState())
        private set

    fun loadCourseDetails(courseId: Int) {
        viewModelScope.launch {
            uiState = uiState.copy(isLoading = true, errorMessage = null)
            try {
                val domainCourse = courseRepository.getCourseById(courseId)

                if (domainCourse != null) {
                    val teacherName = profileRepository.getTeacherName(domainCourse.teacherId).getOrDefault("Desconocido")

                    val visualCourse = CourseUI(
                        id = domainCourse.id,
                        name = domainCourse.name,
                        price = "$ ${domainCourse.price}",
                        docentName = teacherName,
                        description = domainCourse.description ?: "No tiene descripción."
                    )

                    val userId = authRepository.getCurrentUserId() ?: ""
                    val isAlreadyEnrolled = if (userId.isNotEmpty()) {
                        enrollmentRepository.getMyEnrollments(userId).any { it.courseId == courseId }
                    } else false

                    uiState = uiState.copy(
                        isLoading = false,
                        course = visualCourse,
                        isEnrolled = isAlreadyEnrolled
                    )
                } else {
                    uiState = uiState.copy(isLoading = false, errorMessage = "Curso no encontrado")
                }
            } catch (e: Exception) {
                uiState = uiState.copy(isLoading = false, errorMessage = e.message)
            }
        }
    }

    fun enrollInCourse() {
        val currentCourse = uiState.course ?: return
        viewModelScope.launch {
            uiState = uiState.copy(isLoading = true)
            try {
                // Obtenemos el precio real limpio desde el repo otra vez por seguridad
                val domainCourse = courseRepository.getCourseById(currentCourse.id)
                val userId = authRepository.getCurrentUserId()

                if (userId != null && domainCourse != null) {
                    val balanceResult = profileRepository.getStudentBalance(userId)

                    if (balanceResult.isSuccess) {
                        val currentBalance = balanceResult.getOrDefault(0.0)

                        if ((currentBalance - domainCourse.price) >= 0) {
                            val newBalance = currentBalance - domainCourse.price

                            // Transacción
                            enrollmentRepository.enrollStudent(userId, currentCourse.id)
                            profileRepository.updateBalance(newBalance, userId)

                            uiState = uiState.copy(
                                isLoading = false,
                                enrollmentSuccess = true,
                                isEnrolled = true,
                                successMessage = "¡Inscripción exitosa!"
                            )
                        } else {
                            uiState = uiState.copy(isLoading = false, errorMessage = "Saldo insuficiente.")
                        }
                    } else {
                        uiState = uiState.copy(isLoading = false, errorMessage = "Error consultando saldo.")
                    }
                }
            } catch (e: Exception) {
                uiState = uiState.copy(isLoading = false, errorMessage = "Error: ${e.message}")
            }
        }
    }
}
package com.example.meet4learn.ui.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.meet4learn.domain.repositories.AuthRepository
import com.example.meet4learn.domain.repositories.CourseRepository
import com.example.meet4learn.domain.repositories.EnrollmentRepository
import com.example.meet4learn.domain.repositories.ModuleRepository
import com.example.meet4learn.domain.repositories.ProfileRepository
import com.example.meet4learn.ui.utils.CourseUI
import kotlinx.coroutines.launch

class CourseDetailsViewModel(
    private val courseRepository: CourseRepository,
    private val profileRepository: ProfileRepository,
    private val enrollmentRepository: EnrollmentRepository,
    private val authRepository: AuthRepository,
    private val moduleRepository: ModuleRepository
) : ViewModel() {

    var uiState by mutableStateOf(CourseDetailsUiState())
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
                        docentName = teacherName
                    )

                    val modules = moduleRepository.getModulesByCourseId(courseId)

                    uiState = uiState.copy(
                        isLoading = false,
                        modules = modules,
                        course = visualCourse
                    )

                    // 4. (Opcional) Verificar si ya está inscrito
                    // checkEnrollmentStatus(courseId)

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
                val domainCourse = courseRepository.getCourseById(uiState.course!!.id)
                val userId = authRepository.getCurrentUserId()
                if (userId != null && domainCourse != null) {
                    val balanceResult = profileRepository.getStudentBalance(userId)
                    if(balanceResult.isSuccess) {
                        if ((balanceResult.getOrDefault(-1.0) - domainCourse.price) >= 0) {
                            enrollmentRepository.enrollStudent(userId, currentCourse.id)
                            uiState = uiState.copy(isLoading = false, enrollmentSuccess = true, isEnrolled = true)
                        } else {
                            uiState = uiState.copy(isLoading = false, enrollmentSuccess = false, isEnrolled = false,
                                errorMessage = "No tienes saldo suficiente.")
                        }
                    } else {
                        uiState = uiState.copy(isLoading = false, enrollmentSuccess = false, isEnrolled = false,
                            errorMessage = "No se pudo recuperar tu saldo.")
                    }
                }
            } catch (e: Exception) {
                uiState = uiState.copy(isLoading = false, errorMessage = "Error al inscribirse: ${e.message}")
            }
        }
    }

    // Resetear mensaje de éxito tras mostrar Toast
    fun resetEnrollmentSuccess() {
        uiState = uiState.copy(enrollmentSuccess = false)
    }
}

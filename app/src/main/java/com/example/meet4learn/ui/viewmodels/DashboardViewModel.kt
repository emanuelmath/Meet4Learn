package com.example.meet4learn.ui.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.meet4learn.data.mappers.toModel
import com.example.meet4learn.domain.repositories.AuthRepository
import com.example.meet4learn.domain.repositories.CourseRepository
import com.example.meet4learn.domain.repositories.EnrollmentRepository
import com.example.meet4learn.domain.repositories.ProfileRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class DashboardViewModel(private val authRepository: AuthRepository,
                         private val courseRepository: CourseRepository,
                         private val profileRepository: ProfileRepository,
                         private val enrollmentRepository: EnrollmentRepository
): ViewModel() {

    var uiState by mutableStateOf(DashboardUiState())
    private set

    init {
        getAllCourses()
    }

    fun logOut() {
        viewModelScope.launch {
            authRepository.logout()
        }
    }

    fun getAllCourses() {viewModelScope.launch {
        uiState = uiState.copy(isLoading = true)

        try {
            val userId = authRepository.getCurrentUserId() ?: ""

            val enrolledCourseIds = if (userId.isNotEmpty()) {
                enrollmentRepository.getMyEnrollments(userId).map { it.courseId }.toSet()
            } else {
                emptySet()
            }

            courseRepository.getAllCoursesRealtime()
                .catch { error ->
                    uiState = uiState.copy(
                        isLoading = false,
                        errorMessage = error.message ?: "Error desconocido."
                    )
                }
                .collect { listaCursos ->

                    val cursosFiltrados = listaCursos.filter { course ->
                        // Por si acaso... Ya lo hago en el repo.
                        val isActivo = course.status == "activo"
                        val isNotEnrolled = !enrolledCourseIds.contains(course.id)

                        isActivo && isNotEnrolled
                    }

                    val teacherIds = cursosFiltrados.map { it.teacherId }.distinct()
                    val teacherMap = mutableMapOf<String, String>()

                    for (id in teacherIds) {
                        val name = profileRepository.getTeacherName(id).getOrNull() ?: "Profesor"
                        teacherMap[id] = name
                    }

                    uiState = uiState.copy(
                        isLoading = false,
                        courses = cursosFiltrados,
                        errorMessage = null,
                        teacherNames = teacherMap
                    )
                }

        } catch (e: Exception) {
            uiState = uiState.copy(
                isLoading = false,
                errorMessage = "Error cargando datos iniciales."
            )
        }
    }
    }
}
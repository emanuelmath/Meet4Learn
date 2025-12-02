package com.example.meet4learn.ui.viewmodels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.meet4learn.domain.models.Course
import com.example.meet4learn.domain.models.Enrollment
import com.example.meet4learn.domain.repositories.AuthRepository
import com.example.meet4learn.domain.repositories.CourseRepository
import com.example.meet4learn.domain.repositories.EnrollmentRepository
import com.example.meet4learn.domain.repositories.ProfileRepository
import kotlinx.coroutines.launch

class MyCoursesViewModel(private val courseRepository: CourseRepository,
                         private val enrollmentRepository: EnrollmentRepository,
                         private val authRepository: AuthRepository,
                         private val profileRepository: ProfileRepository
    ) : ViewModel() {

    var uiState by mutableStateOf(MyCoursesUiState())
        private set

    private val id: String by lazy {
        authRepository.getCurrentUserId() ?: ""
    }

    init {
        if (id.isNotEmpty()) {
            getMyCourses()
        }
    }
    private suspend fun getMyEnrollments(studentId: String): List<Enrollment> {
        return enrollmentRepository.getMyEnrollments(studentId)
    }

    private suspend fun getCourseById(idCourse: Int): Course? {
        return courseRepository.getCourseById(idCourse)
    }

    fun getMyCourses(isRefresh: Boolean = false) {
        viewModelScope.launch {
            if (isRefresh) uiState = uiState.copy(isRefreshing = true)
            else uiState = uiState.copy(isLoading = true)

            try {
                val studentId = id

                val enrollments = getMyEnrollments(studentId)
                val courseIds = enrollments.map { it.courseId }

                val courseList = courseRepository.getCoursesByIds(courseIds)

                val teacherIds = courseList.map { it.teacherId }.distinct()
                val namesMap = mutableMapOf<String, String>()

                for (tId in teacherIds) {
                    val result = profileRepository.getTeacherName(tId)
                    val name = result.getOrNull() ?: "Desconocido"
                    namesMap[tId] = name
                }

                uiState = uiState.copy(
                    myEnrollments = enrollments,
                    myCourses = courseList,
                    teacherNames = namesMap,
                    isLoading = false,
                    isRefreshing = false
                )

            } catch (e: Exception) {
                Log.e("MyCoursesVM", "Error", e)
                uiState = uiState.copy(isLoading = false, isRefreshing = false)
            }
        }
    }

}
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
import kotlinx.coroutines.launch

class MyCoursesViewModel(private val courseRepository: CourseRepository,
                         private val enrollmentRepository: EnrollmentRepository,
                         private val authRepository: AuthRepository
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
    fun getMyEnrollments(studentId: String) {
        viewModelScope.launch {
            uiState.copy(myEnrollments = enrollmentRepository.getMyEnrollments(studentId))
        }
    }

    fun getCourseById(idCourse: Int): Course? {
        var course: Course? = null
        viewModelScope.launch {
             course = courseRepository.getCourseById(idCourse)
        }
        return course
    }

    fun getMyCourses() {
        viewModelScope.launch {
            val studentId = id

            getMyEnrollments(studentId)

            val courseList = mutableListOf<Course>()
            for (enrollment in uiState.myEnrollments) {
                Log.e("ENROLLMENT", "${enrollment.courseId}")
                val course = getCourseById(enrollment.courseId)
                if (course != null) courseList.add(course)
            }

            uiState = uiState.copy(myCourses = courseList, isLoading = false)
        }
    }

}
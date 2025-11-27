package com.example.meet4learn.ui.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.meet4learn.data.mappers.toModel
import com.example.meet4learn.domain.repositories.AuthRepository
import com.example.meet4learn.domain.repositories.CourseRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class DashboardViewModel(private val authRepository: AuthRepository, private val courseRepository: CourseRepository): ViewModel() {

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

    fun getAllCourses() {
        viewModelScope.launch {
            uiState = uiState.copy(isLoading = true)

            courseRepository.getAllCoursesRealtime()
                .catch { error ->
                    uiState = uiState.copy(
                        isLoading = false,
                        errorMessage = error.message ?: "Error desconocido"
                    )
                }
                .collect { listaCursos ->
                    uiState = uiState.copy(
                        isLoading = false,
                        courses = listaCursos,
                        errorMessage = null
                    )
                }
        }
    }
}
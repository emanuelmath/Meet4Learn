package com.example.meet4learn.domain.repositories

import com.example.meet4learn.domain.models.Module

interface ModuleRepository {
    suspend fun getModuleByID()
    suspend fun getModulesByCourseId(courseId: Int): List<Module>
}
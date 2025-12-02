package com.example.meet4learn.di

import android.content.Context
import com.example.meet4learn.BuildConfig
import io.github.jan.supabase.auth.Auth
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.realtime.Realtime
import io.github.jan.supabase.storage.Storage
import io.github.jan.supabase.functions.Functions
import com.example.meet4learn.domain.repositories.AuthRepository
import com.example.meet4learn.data.repositories.AuthRepositoryImpl
import com.example.meet4learn.data.repositories.CourseRepositoryImpl
import com.example.meet4learn.data.repositories.EnrollmentRepositoryImpl
import com.example.meet4learn.data.repositories.ModuleRepositoryImpl
import com.example.meet4learn.data.repositories.ProfileRepositoryImpl
import com.example.meet4learn.domain.repositories.CourseRepository
import com.example.meet4learn.domain.repositories.EnrollmentRepository
import com.example.meet4learn.domain.repositories.ModuleRepository
import com.example.meet4learn.domain.repositories.ProfileRepository
import com.example.meet4learn.domain.usecase.LoginStudentUseCase
import com.example.meet4learn.domain.usecase.RegisterStudentUseCase
import io.ktor.client.engine.okhttp.OkHttp


class AppContainer (context: Context) {
    //Cliente de Supabase.
    val supabaseClient = createSupabaseClient(
        supabaseUrl = BuildConfig.SUPABASE_URL,
        supabaseKey = BuildConfig.SUPABASE_ANON_KEY
    ) {
        httpEngine = OkHttp.create()

        install(Auth)
        install(Postgrest)
        install(Realtime)
        install(Storage)
        install(Functions)
    }

    // Repositorios.
    val authRepository: AuthRepository by lazy {
        AuthRepositoryImpl(supabaseClient)
    }
    val profileRepository: ProfileRepository by lazy {
        ProfileRepositoryImpl(supabaseClient)
    }
    val courseRepository: CourseRepository by lazy {
        CourseRepositoryImpl(supabaseClient)
    }
    val enrollmentRepository: EnrollmentRepository by lazy {
        EnrollmentRepositoryImpl(supabaseClient)
    }
    val moduleRepository: ModuleRepository by lazy {
        ModuleRepositoryImpl(supabaseClient)
    }
    //UseCases.
    val loginStudentUseCase: LoginStudentUseCase by lazy {
        LoginStudentUseCase(authRepository, profileRepository)
    }
    val registerStudentUseCase: RegisterStudentUseCase by lazy {
        RegisterStudentUseCase(authRepository)
    }

}
package com.example.meet4learn.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.meet4learn.di.AppContainer
import com.example.meet4learn.ui.navigation.Screen
import com.example.meet4learn.ui.utils.Meet4LearnBottomBar
import com.example.meet4learn.ui.utils.Meet4LearnTopBar
import com.example.meet4learn.ui.viewmodels.DashboardViewModel
import com.example.meet4learn.ui.viewmodels.DashboardViewModelFactory

@Composable
fun MainScreen(
    navController: NavHostController,
    appContainer: AppContainer
) {
    val homeNavController = rememberNavController()

    Scaffold(
        topBar = { Meet4LearnTopBar() },
        bottomBar = { Meet4LearnBottomBar(navController = homeNavController) }
    ) { paddingValues ->

        NavHost(
            navController = homeNavController,
            startDestination = Screen.Dashboard.route,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(Screen.Dashboard.route) {
                val dashboardViewModel: DashboardViewModel = viewModel(
                    factory = DashboardViewModelFactory(
                        appContainer.authRepository,
                        appContainer.courseRepository,
                        appContainer.profileRepository
                    )
                )
                DashboardScreen(navController = homeNavController, dashboardViewModel = dashboardViewModel)
            }

            composable("courses_internal") {
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) { Text("Mis Cursos") }
            }

            composable("calendar_internal") {
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) { Text("Calendario") }
            }

            composable("profile_internal") {
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) { Text("Perfil") }
            }
        }
    }
}
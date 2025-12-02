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
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.meet4learn.di.AppContainer
import com.example.meet4learn.ui.navigation.Screen
import com.example.meet4learn.ui.components.Meet4LearnBottomBar
import com.example.meet4learn.ui.components.Meet4LearnTopBar
import com.example.meet4learn.ui.utils.CourseUI
import com.example.meet4learn.ui.viewmodels.CourseDescriptionViewModel
import com.example.meet4learn.ui.viewmodels.CourseDescriptionViewModelFactory
import com.example.meet4learn.ui.viewmodels.CourseDetailsViewModel
import com.example.meet4learn.ui.viewmodels.CourseDetailsViewModelFactory
import com.example.meet4learn.ui.viewmodels.DashboardViewModel
import com.example.meet4learn.ui.viewmodels.DashboardViewModelFactory
import com.example.meet4learn.ui.viewmodels.MyCoursesViewModel
import com.example.meet4learn.ui.viewmodels.MyCoursesViewModelFactory
import com.example.meet4learn.ui.viewmodels.StudentProfileViewModel
import com.example.meet4learn.ui.viewmodels.StudentProfileViewModelFactory

@Composable
fun MainScreen(
    navController: NavHostController,
    appContainer: AppContainer
) {
    val homeNavController = rememberNavController()
    val dashboardViewModel: DashboardViewModel = viewModel(
        factory = DashboardViewModelFactory(appContainer.authRepository, appContainer.courseRepository, appContainer.profileRepository, appContainer.enrollmentRepository)
    )
    val myCoursesViewModel: MyCoursesViewModel = viewModel(
        factory = MyCoursesViewModelFactory(appContainer.courseRepository, appContainer.enrollmentRepository, appContainer.authRepository, appContainer.profileRepository)
    )

    val studentProfileViewModel: StudentProfileViewModel = viewModel(
        factory = StudentProfileViewModelFactory(
            appContainer.savedPaymentMethodRepository,
            appContainer.profileRepository,
            appContainer.authRepository
        )
    )
    val courseDetailsViewModel: CourseDetailsViewModel = viewModel(
        factory = CourseDetailsViewModelFactory(
            appContainer.courseRepository,
            appContainer.profileRepository,
            appContainer.moduleRepository
        )
    )

    val courseDescriptionViewModel: CourseDescriptionViewModel = viewModel(
        factory = CourseDescriptionViewModelFactory(
            appContainer.courseRepository,
            appContainer.profileRepository,
            appContainer.enrollmentRepository,
            appContainer.authRepository
        )
    )

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
                DashboardScreen(navController = homeNavController, dashboardViewModel = dashboardViewModel)
            }

            composable(Screen.MyCourses.route) {

                MyCoursesScreen(myCoursesViewModel, homeNavController)
            }

            composable(Screen.Calendar.route) {
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) { Text("Calendario") }
            }

            composable(Screen.Profile.route) {
                UsuarioScreen(studentProfileViewModel)
            }

            composable(
                route = Screen.CourseDetails.route,
                arguments = listOf(navArgument("courseId") { type = NavType.IntType })
            ) { backStackEntry ->
                val courseId = backStackEntry.arguments?.getInt("courseId") ?: return@composable

                DescripcionCursosScreen(
                    courseId = courseId,
                    courseDescriptionViewModel = courseDescriptionViewModel,
                    navController = homeNavController
                )
            }

            composable(
                route = Screen.MyCourseDetails.route,
                arguments = listOf(navArgument("courseId") { type = NavType.IntType })
            ) { backStackEntry ->
                val courseId = backStackEntry.arguments?.getInt("courseId") ?: return@composable

                DetallesCursoScreen(
                    courseId = courseId,
                    courseDetailsViewModel = courseDetailsViewModel,
                    navController = homeNavController,
                    onJoinCall = { moduleId ->
                        navController.navigate(Screen.VideoCall.moduleSelected(moduleId))
                    }
                )
            }
        }
    }
}
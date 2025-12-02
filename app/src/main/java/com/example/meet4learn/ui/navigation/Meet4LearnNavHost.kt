package com.example.meet4learn.ui.navigation

import android.app.Application
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.meet4learn.di.AppContainer
import com.example.meet4learn.ui.screens.DashboardScreen
import com.example.meet4learn.ui.screens.LlamadaScreen
import com.example.meet4learn.ui.screens.LoginScreen
import com.example.meet4learn.ui.screens.MainScreen
import com.example.meet4learn.ui.screens.RegisterScreen
import com.example.meet4learn.ui.screens.SplashScreen
import com.example.meet4learn.ui.viewmodels.DashboardViewModel
import com.example.meet4learn.ui.viewmodels.DashboardViewModelFactory
import com.example.meet4learn.ui.viewmodels.LoginViewModel
import com.example.meet4learn.ui.viewmodels.LoginViewModelFactory
import com.example.meet4learn.ui.viewmodels.RegisterViewModel
import com.example.meet4learn.ui.viewmodels.RegisterViewModelFactory
import com.example.meet4learn.ui.viewmodels.SplashViewModel
import com.example.meet4learn.ui.viewmodels.SplashViewModelFactory
import com.example.meet4learn.ui.viewmodels.VideoCallViewModel
import com.example.meet4learn.ui.viewmodels.VideoCallViewModelFactory

@Composable
fun Meet4LearnNavHost(navHostController: NavHostController,
                      appContainer: AppContainer,
                      startDestination: String) {
    NavHost(navController = navHostController, startDestination = startDestination) {
        composable(Screen.Login.route) {
            val loginViewModel: LoginViewModel = viewModel(
                factory = LoginViewModelFactory(appContainer.loginStudentUseCase)
            )
            LoginScreen(navHostController, loginViewModel)
        }
        composable(Screen.Register.route) {
            val registerViewModel: RegisterViewModel = viewModel(
                factory = RegisterViewModelFactory(appContainer.registerStudentUseCase)
            )
            RegisterScreen(navHostController, registerViewModel)
        }
        composable(Screen.Main.route) {
            MainScreen(
                navController = navHostController,
                appContainer = appContainer
            )
        }
        composable(Screen.Splash.route) {
            val splashViewModel: SplashViewModel = viewModel(
                factory = SplashViewModelFactory(appContainer.authRepository)
            )
            SplashScreen(navHostController, splashViewModel)
        }
        composable(
            route = Screen.VideoCall.route,
            arguments = listOf(navArgument("moduleId") { type = NavType.IntType })
        ) { backStackEntry ->
            val moduleId = backStackEntry.arguments?.getInt("moduleId") ?: 0

            val videoViewModel: VideoCallViewModel = viewModel(
                factory = VideoCallViewModelFactory(
                    application = LocalContext.current.applicationContext as Application,
                    chatRepository = appContainer.chatMessageRepository,
                    callRepository = appContainer.callRepository,
                    authRepository = appContainer.authRepository,
                    profileRepository = appContainer.profileRepository
                )
            )

            LlamadaScreen(
                moduleId = moduleId,
                navController = navHostController,
                videoCallViewModel = videoViewModel
            )
        }
    }
}
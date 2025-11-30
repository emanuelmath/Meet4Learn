package com.example.meet4learn.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.meet4learn.R
import com.example.meet4learn.ui.navigation.Screen
import com.example.meet4learn.ui.theme.Oswald
import com.example.meet4learn.ui.viewmodels.SplashViewModel
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController, splashViewModel: SplashViewModel) {
    LaunchedEffect(Unit) {
        delay(1000)

        if (splashViewModel.isUserLoggedIn()) {
            navController.navigate(Screen.Main.route) {
                popUpTo(Screen.Splash.route) { inclusive = true }
            }
        } else {
            navController.navigate(Screen.Login.route) {
                popUpTo(Screen.Splash.route) { inclusive = true }
            }
        }
    }

    Box(
        modifier = Modifier.fillMaxSize().background(Color(0xFF001E3C)),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(painter = painterResource(R.drawable.logomeet4learn), contentDescription = null)
            Spacer(modifier = Modifier.size(8.dp))
            Text(
                text = "Meet4Learn",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontFamily = Oswald
            )
            Spacer(modifier = Modifier.size(16.dp))
            CircularProgressIndicator()
        }
    }
}
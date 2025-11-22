package com.example.meet4learn.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.meet4learn.ui.navigation.Screen
import com.example.meet4learn.ui.viewmodels.DashboardViewModel

@Composable
fun DashboardScreen(navController: NavController, dashboardViewModel: DashboardViewModel) {
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(top = 50.dp)) {
        Text("Dashboard bien chivo.")
        Text("C# > Kotlin ^ Java.")
        Spacer(modifier = Modifier.size(25.dp))
        Button(onClick = {
            dashboardViewModel.logOut()
            navController.navigate(Screen.Login.route) {
                popUpTo(Screen.Dashboard.route) { inclusive = true }
            }
        }) { Text("Cerrar Sesión") }
    }
}
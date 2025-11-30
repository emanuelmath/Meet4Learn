package com.example.meet4learn.ui.utils

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.meet4learn.ui.navigation.Screen
import com.example.meet4learn.ui.theme.*

@Composable
fun Meet4LearnBottomBar(navController: NavController) {
    NavigationBar(
        containerColor = BluePrimary,
        contentColor = TextColorLight,
        modifier = Modifier
            .navigationBarsPadding()
            .height(64.dp)
    ) {

        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        BottomNavItem(
            icon = Icons.Default.Home,
            label = "Home",
            isSelected = currentRoute == Screen.Dashboard.route,
            onClick = { if(currentRoute != Screen.Dashboard.route) {
                    navController.navigate(Screen.Dashboard.route)
                }
            }
        )
        BottomNavItem(
            icon = Icons.Default.Star,
            label = "Mis Cursos",
            isSelected = currentRoute == "courses_internal",
            onClick = { navController.navigate("courses_internal") }
        )
        BottomNavItem(
            icon = Icons.Default.DateRange,
            label = "Calendario",
            isSelected = currentRoute == "calendar_internal",
            onClick = { navController.navigate("calendar_internal") }
        )
        BottomNavItem(
            icon = Icons.Default.Person,
            label = "Perfil",
            isSelected = currentRoute == "profile_internal",
            onClick = { navController.navigate("profile_internal") }
        )
    }
}

@Composable
fun RowScope.BottomNavItem(
    icon: ImageVector,
    label: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val contentColor = if (isSelected) Color(0xFF5A9DFF) else TextColorLight
    NavigationBarItem(
        icon = { Icon(icon, contentDescription = label, tint = contentColor) },
        label = { Text(label, color = contentColor, fontSize = 10.sp) },
        selected = isSelected,
        onClick = onClick,
        colors = NavigationBarItemDefaults.colors(indicatorColor = BluePrimary)
    )
}
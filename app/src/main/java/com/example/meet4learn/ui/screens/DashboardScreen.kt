package com.example.meet4learn.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.meet4learn.ui.navigation.Screen
import com.example.meet4learn.ui.viewmodels.DashboardViewModel
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import com.example.meet4learn.R
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.material3.Scaffold
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults


@Composable
fun DashboardScreen(navController: NavController, dashboardViewModel: DashboardViewModel) {

    val uiState = dashboardViewModel.uiState

    /*LaunchedEffect(Unit) {
        dashboardViewModel.getAllCourses()
    } Ya no por el init*/

    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(top = 50.dp)) {
        Text("Dashboard no oficial.")
        Text("C# > Kotlin ^ Java.")
        Spacer(modifier = Modifier.size(25.dp))
        Button(onClick = {
            dashboardViewModel.logOut()
            navController.navigate(Screen.Login.route) {
                popUpTo(Screen.Dashboard.route) { inclusive = true }
            }
        }) { Text("Cerrar Sesión") }


        LazyColumn {
          items(uiState.courses) { course ->
              Row(verticalAlignment = Alignment.CenterVertically) {
                  Text(course.name)
                  Spacer(modifier = Modifier.size(1.dp))
                  Text(course.status)
                  Spacer(modifier = Modifier.size(1.dp))
                  Text(course.price.toString())
              }
        }
        }
    }
}


val BluePrimary = Color(0xFF001E3C)
val BlueCard = Color(0xFF5A9DFF)
val GreenButton = Color(0xFF4CAF50)
val TextColorDark = Color(0xFF000000)
val TextColorLight = Color(0xFFFFFFFF)


data class Course(
    val title: String,
    val price: String,
    val docentName: String,
    val docentImage: Int
)


val sampleCourses = listOf(
    Course("Álgebra Básica", "$ 0.00", "Rodrigo Contreras", 0), // Placeholder simple
    Course("Introducción a la Cocina", "$ 25.00", "Ana Gómez", 0),
    Course("Marketing", "$ 50.00", "Carlos Perez", 0)
)

@Composable
fun DashboardScreen2() {
    Scaffold(
        topBar = { AppTopBar() },
        bottomBar = { AppBottomBar() },
        content = { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .background(Color.White)
            ) {

                Text(
                    text = "Cursos Disponibles",
                    style = MaterialTheme.typography.headlineLarge.copy(
                        fontWeight = FontWeight.Black,
                        fontSize = 28.sp,
                        color = TextColorDark
                    ),
                    modifier = Modifier.padding(start = 16.dp, top = 16.dp, bottom = 8.dp)
                )


                LazyColumn(
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(sampleCourses) { course ->
                        CourseCard(course = course)
                    }
                }
            }
        }
    )
}

@Composable
fun AppTopBar() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .background(BluePrimary)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(painterResource(R.drawable.logoprincipal), contentDescription = null)
        }
    }
}

@Composable
fun AppBottomBar() {
    NavigationBar(
        containerColor = BluePrimary,
        contentColor = TextColorLight,
        modifier = Modifier.height(64.dp)
    ) {
        BottomNavItem(icon = Icons.Default.Home, label = "Home", )
        BottomNavItem(icon = Icons.Default.Star, label = "Mis Cursos", )
        BottomNavItem(icon = Icons.Default.DateRange, label = "Calendario", )
        BottomNavItem(icon = Icons.Default.Person, label = "Perfil",)
    }
}

@Composable
fun RowScope.BottomNavItem(
    icon: ImageVector,
    label: String,
    isSelected: Boolean = false
) {
    val contentColor = if (isSelected) Color(0xFF5A9DFF) else TextColorLight

    NavigationBarItem(
        selected = isSelected,
        onClick = { },
        icon = {
            Icon(
                imageVector = icon,
                contentDescription = label,
                tint = contentColor
            )
        },
        label = {
            Text(
                text = label,
                color = contentColor,
                fontSize = 10.sp
            )
        },
        colors = NavigationBarItemDefaults.colors(
            indicatorColor = BluePrimary
        )
    )
}


@Composable
fun CourseCard(course: Course) {
    Card(
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier
            .fillMaxWidth()
            .shadow(
                elevation = 12.dp,
                shape = RoundedCornerShape(12.dp),
                spotColor = Color.Black.copy(alpha = 1f)
            )
            .background(Color.Transparent),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(BlueCard, RoundedCornerShape(12.dp))
                .padding(16.dp)
        ) {

            Text(
                text = "Nombre del curso:",
                color = TextColorDark,
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold
            )
            Text(
                text = course.title,
                color = TextColorDark,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = "Precio: ${course.price}",
                        color = TextColorDark,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier
                            .clip(RoundedCornerShape(12.dp))
                            .background(Color.White)
                            .padding(horizontal = 6.dp, vertical = 2.dp)
                    )
                }


                Column(
                    horizontalAlignment = Alignment.End,
                    modifier = Modifier.weight(1f)
                ) {

                    Row(
                        modifier = Modifier
                            .clip(RoundedCornerShape(8.dp))
                            .background(Color.White)
                            .padding(4.dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {

                        Image(
                            painter = painterResource(id = R.drawable.libro_abierto),
                            contentDescription = "Docente: ${course.docentName}",
                            modifier = Modifier
                                .size(32.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .background(Color.LightGray)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Docente:\n${course.docentName}",
                            color = TextColorDark,
                            fontSize = 12.sp,
                            lineHeight = 14.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))


                    Button(
                        onClick = {},
                        colors = ButtonDefaults.buttonColors(containerColor = GreenButton),
                        shape = RoundedCornerShape(12.dp),
                        contentPadding = PaddingValues(horizontal = 12.dp, vertical = 6.dp),
                        modifier = Modifier
                            .height(30.dp)

                    ) {
                        Text(
                            text = "VER DETALLES",
                            color = TextColorLight,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DashboardScreenPreview() {
    MaterialTheme {
        DashboardScreen2()
    }
}
package com.example.meet4learn.ui.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.ui.text.font.FontWeight
import com.example.meet4learn.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.lazy.*
import androidx.compose.material.icons.filled.Call
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.meet4learn.domain.models.Module
import com.example.meet4learn.ui.navigation.Screen
import com.example.meet4learn.ui.theme.*
import com.example.meet4learn.ui.utils.CourseUI
import com.example.meet4learn.ui.viewmodels.CourseDetailsViewModel


@Composable
fun DetallesCursoScreen(
    courseId: Int,
    viewModel: CourseDetailsViewModel,
    navController: NavController
) {
    LaunchedEffect(courseId) {
        viewModel.loadCourseDetails(courseId)
    }

    val uiState = viewModel.uiState

    if (uiState.isLoading) {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator(color = BluePrimary)
        }
        return
    }

    if (uiState.errorMessage != null) {
        Toast.makeText(LocalContext.current,
            uiState.errorMessage, Toast.LENGTH_LONG).show()
    }

    val course = uiState.course

    if (course != null) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(16.dp)
        ) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFF9FD5FF)),
                elevation = CardDefaults.cardElevation(6.dp)
            ) {
                Column(modifier = Modifier.padding(20.dp)) {
                    Text(
                        text = course.name.uppercase(),
                        fontWeight = FontWeight.Black,
                        fontSize = 18.sp,
                        color = Color(0xFF0D1B2A)
                    )
                    Text(
                        text = "DOCENTE: ${course.docentName.uppercase()}",
                        fontSize = 14.sp,
                        color = Color(0xFF0D1B2A)
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(14.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFF1E56A0)),
                elevation = CardDefaults.cardElevation(8.dp)
            ) {
                Column(modifier = Modifier.padding(20.dp)) {
                    Text(text = "PRECIO:", color = Color.White, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = course.price,
                        color = Color.White,
                        fontWeight = FontWeight.Black,
                        fontSize = 16.sp
                    )
                    Spacer(modifier = Modifier.height(12.dp))

                    Button(
                        onClick = { viewModel.enrollInCourse() },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0A2C52)),
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier.align(Alignment.End),
                        enabled = !uiState.isEnrolled
                    ) {
                        Icon(Icons.Default.Call, contentDescription = null, tint = Color.White)
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(if (uiState.isEnrolled) "INSCRITO" else "UNIRSE", color = Color.White)
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                shape = RoundedCornerShape(14.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFF7DB9FF)),
                elevation = CardDefaults.cardElevation(6.dp)
            ) {
                Column(modifier = Modifier.padding(20.dp)) {
                    Text(text = "MÓDULOS", fontWeight = FontWeight.Black, color = Color(0xFF0D1B2A))
                    Spacer(modifier = Modifier.height(12.dp))
                    if(uiState.modules == emptyList<Module>()){
                        Text("Aún no hay módulos en este curso.")
                    } else {
                        LazyColumn {
                            items(uiState.modules) { module ->
                                ModuleItem(module.title)
                            }
                        }
                    }
                }
            }

            Button(
                onClick = { navController.navigate(Screen.Main.route) },
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1E56A0))
            ) {
                Text("VOLVER")
            }
        }
    }
}

@Composable
fun ModuleItem(texto: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFFFEB61)
        ),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Text(
            text = texto,
            modifier = Modifier.padding(12.dp),
            fontWeight = FontWeight.SemiBold,
            fontSize = 14.sp,
            color = Color(0xFF0D1B2A)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DetallesCursoScreenPreview() {
    MaterialTheme {
        //DetallesCursoScreen()
    }
}

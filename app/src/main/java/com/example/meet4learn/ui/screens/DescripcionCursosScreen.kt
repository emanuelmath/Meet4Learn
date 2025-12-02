package com.example.meet4learn.ui.screens

import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import com.example.meet4learn.R
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.meet4learn.ui.navigation.Screen
import com.example.meet4learn.ui.theme.BluePrimary
import com.example.meet4learn.ui.viewmodels.CourseDescriptionViewModel
import com.example.meet4learn.ui.viewmodels.CourseDetailsViewModel

@Composable
fun DescripcionCursosScreen(
    courseId: Int,
    courseDescriptionViewModel: CourseDescriptionViewModel,
    navController: NavController
) {
    BackHandler(enabled = true) {}

    LaunchedEffect(courseId) {
        courseDescriptionViewModel.loadCourseDetails(courseId)
    }

    val uiState = courseDescriptionViewModel.uiState


    if (uiState.errorMessage != null) {
        Toast.makeText(LocalContext.current,
            uiState.errorMessage, Toast.LENGTH_LONG).show()
    }

    if (uiState.successMessage != null) {
        Toast.makeText(LocalContext.current,
            uiState.successMessage, Toast.LENGTH_LONG).show()
    }

    if (uiState.isLoading) {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator(color = BluePrimary)
        }
        return
    }

    val course = uiState.course ?: return

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {


        Column(
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .weight(1f)
                .verticalScroll(rememberScrollState())
                .padding(top = 15.dp)
        ) {
            Surface(
                shape = RoundedCornerShape(16.dp),
                color = Color(0xFF1E40AF),
                shadowElevation = 12.dp
            ) {
                Column(modifier = Modifier.padding(20.dp)) {

                    Text("NOMBRE DEL CURSO:", color = Color.White, fontWeight = FontWeight.ExtraBold, fontSize = 16.sp)
                    Spacer(modifier = Modifier.height(6.dp))
                    Box(Modifier.fillMaxWidth().background(Color(0xFF60A5FA), RoundedCornerShape(10.dp)).padding(14.dp)) {
                        Text(course.name.uppercase(), color = Color.White, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                    }

                    Spacer(modifier = Modifier.height(18.dp))

                    Text("DOCENTE:", color = Color.White, fontWeight = FontWeight.ExtraBold, fontSize = 16.sp)
                    Spacer(modifier = Modifier.height(6.dp))
                    Box(Modifier.fillMaxWidth().background(Color(0xFF93C5FD), RoundedCornerShape(10.dp)).padding(14.dp)) {
                        Text(course.docentName.uppercase(), color = Color.White, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    Box(Modifier.fillMaxWidth().background(Color(0xFF0F172A), RoundedCornerShape(12.dp)).padding(16.dp)) {
                        Column {
                            Text("DESCRIPCIÓN:", color = Color.White, fontWeight = FontWeight.ExtraBold, fontSize = 16.sp)
                            Spacer(modifier = Modifier.height(6.dp))
                            Text(
                                text = course.description.ifEmpty { "Sin descripción disponible." },
                                color = Color.White,
                                fontSize = 14.sp
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text("PRECIO:", color = Color.White, fontWeight = FontWeight.ExtraBold, fontSize = 16.sp)
                        Spacer(modifier = Modifier.width(20.dp))
                        Box(Modifier.background(Color(0xFFFFE4E6), RoundedCornerShape(10.dp)).padding(horizontal = 22.dp, vertical = 10.dp)) {
                            Text(
                                text = course.price,
                                color = Color.Black,
                                fontWeight = FontWeight.ExtraBold,
                                fontSize = 20.sp
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth().padding(20.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Button(
                onClick = { navController.popBackStack() },
                colors = ButtonDefaults.buttonColors(Color(0xFF1E3A8A)),
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier.weight(1f).height(50.dp)
            ) {
                Icon(Icons.Default.ArrowBack, null, tint = Color.White)
                Text(" VOLVER", color = Color.White, fontWeight = FontWeight.Bold)
            }

            Button(
                onClick = { courseDescriptionViewModel.enrollInCourse() },
                colors = ButtonDefaults.buttonColors(if (uiState.isEnrolled) Color.Gray else Color(0xFF22C55E)),
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier.weight(1f).height(50.dp),
                enabled = !uiState.isEnrolled
            ) {
                Text(if (uiState.isEnrolled) "YA INSCRITO" else "INSCRIBIRSE", color = Color.White, fontWeight = FontWeight.Bold)
            }
        }
    }
}
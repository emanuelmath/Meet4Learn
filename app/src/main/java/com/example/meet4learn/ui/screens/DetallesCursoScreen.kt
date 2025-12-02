package com.example.meet4learn.ui.screens

import android.widget.Toast
import androidx.activity.compose.BackHandler
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
import androidx.compose.material.icons.filled.PlayArrow
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
    courseDetailsViewModel: CourseDetailsViewModel,
    navController: NavController,
    onJoinCall: (Int) -> Unit
) {

    BackHandler(enabled = true) {}

    LaunchedEffect(courseId) {
        courseDetailsViewModel.loadCourseDetails(courseId)
    }

    val uiState = courseDetailsViewModel.uiState
    val course = uiState.course

    if (uiState.isLoading) {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) { CircularProgressIndicator() }
        return
    }

    if (uiState.isDialogOpen && uiState.selectedModule != null) {
        ModuleInfoDialog(
            module = uiState.selectedModule,
            onDismiss = { courseDetailsViewModel.dismissDialog() },
            onJoinClick = {
                courseDetailsViewModel.dismissDialog()
                onJoinCall(uiState.selectedModule.id)
            }
        )
    }

    if (course != null) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(16.dp)
        ) {

            Text(
                text = "Mi Aula Virtual",
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold,
                color = TextColorDark
            )

            Spacer(modifier = Modifier.height(16.dp))

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

            Text(text = "MÓDULOS", fontWeight = FontWeight.Black, color = Color(0xFF0D1B2A), fontSize = 18.sp)
            Spacer(modifier = Modifier.height(12.dp))

            if(uiState.modules.isEmpty()){
                Box(Modifier.fillMaxWidth().padding(20.dp), contentAlignment = Alignment.Center) {
                    Text("El docente aún no ha publicado módulos.", color = Color.Gray)
                }
            } else {
                LazyColumn(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(uiState.modules) { module ->
                        ModuleItem(
                            texto = module.title,
                            onClick = {
                                courseDetailsViewModel.openModuleDialog(module)
                            }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { navController.popBackStack() },
                modifier = Modifier.fillMaxWidth().height(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1E56A0)),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text("VOLVER A MIS CURSOS", fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Composable
fun ModuleItem(texto: String, onClick: () -> Unit) {
    Card(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFFFEB61)), // Amarillo
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(imageVector = Icons.Default.PlayArrow, contentDescription = null, tint = Color(0xFF0D1B2A))
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = texto,
                fontWeight = FontWeight.SemiBold,
                fontSize = 14.sp,
                color = Color(0xFF0D1B2A)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DetallesCursoScreenPreview() {
    MaterialTheme {
        //DetallesCursoScreen()
    }
}

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
import androidx.compose.material3.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import com.example.meet4learn.ui.theme.*
import com.example.meet4learn.ui.utils.CourseUI

@Composable
fun DashboardScreen(
    navController: NavController,
    dashboardViewModel: DashboardViewModel
) {
    val uiState = dashboardViewModel.uiState

    val uiCourses = uiState.courses.map { course ->
        CourseUI(
            id = course.id,
            name = course.name,
            price = "$ ${course.price}",
            docentName = uiState.teacherNames[course.teacherId] ?: "Desconocido"
        )
    }
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
            ) {

                Text(
                    text = "Cursos Disponibles",
                    fontWeight = FontWeight.Bold,
                    fontSize = 28.sp,
                    color = TextColorDark,
                    modifier = Modifier.padding(start = 16.dp, top = 16.dp, bottom = 8.dp)
                )

                if (uiState.isLoading) {
                    Box(
                        modifier = Modifier.fillMaxWidth().padding(16.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(color = BluePrimary)
                    }
                }

                if (uiState.errorMessage != null) {
                    Text(
                        text = uiState.errorMessage,
                        color = Color.Red,
                        modifier = Modifier.padding(16.dp)
                    )
                }

                LazyColumn(
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(uiCourses) { course ->
                        CourseCard(course = course, onDetailClick = {
                            navController.navigate(Screen.CourseDetails.courseSelected(course.id))
                        })
                    }
                }
            }
}

@Composable
    fun CourseCard(course: CourseUI, onDetailClick: () -> Unit) {
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
                    text = course.name,
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

                    Column(modifier = Modifier.weight(1f)) {
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
                            onClick = onDetailClick,
                            colors = ButtonDefaults.buttonColors(containerColor = GreenButton),
                            shape = RoundedCornerShape(12.dp),
                            contentPadding = PaddingValues(horizontal = 12.dp, vertical = 6.dp),
                            modifier = Modifier.height(30.dp)
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
        //DashboardScreen2()
    }
}

package com.example.meet4learn.ui.screens


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.meet4learn.R
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.navigation.NavHostController
import com.example.meet4learn.ui.navigation.Screen
import com.example.meet4learn.ui.theme.*
import com.example.meet4learn.ui.viewmodels.MyCoursesViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyCoursesScreen(
    myCourseViewModel: MyCoursesViewModel,
    navController: NavHostController
) {
    val uiState = myCourseViewModel.uiState

    val lifecycleOwner = LocalLifecycleOwner.current
    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { source, event ->
            if (event == Lifecycle.Event.ON_RESUME) {
                myCourseViewModel.getMyCourses(isRefresh = true)
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose { lifecycleOwner.lifecycle.removeObserver(observer) }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .weight(1f)
        ) {

            Text(
                text = "Mis Cursos",
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold,
                color = TextColorDark
            )

            Spacer(modifier = Modifier.height(16.dp))

            var searchText by remember { mutableStateOf("") }

            OutlinedTextField(
                value = searchText,
                onValueChange = { searchText = it },
                placeholder = { Text("Buscar en mis cursos...") },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
                modifier = Modifier
                    .fillMaxWidth()
                    .shadow(4.dp, RoundedCornerShape(25.dp)),
                shape = RoundedCornerShape(25.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedContainerColor = Color.White,
                    focusedContainerColor = Color.White
                )
            )

            Spacer(modifier = Modifier.height(24.dp))

            if (uiState.isLoading) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            } else {
                PullToRefreshBox(
                    isRefreshing = uiState.isRefreshing,
                    onRefresh = { myCourseViewModel.getMyCourses(isRefresh = true) },
                    modifier = Modifier.fillMaxSize()
                ) {

                    val filteredCourses = uiState.myCourses.filter {
                        it.name.contains(searchText, ignoreCase = true)
                    }

                    if (filteredCourses.isEmpty()) {
                        Box(
                            modifier = Modifier.fillMaxSize()
                                .verticalScroll(rememberScrollState()),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = if (searchText.isNotBlank()) {
                                    "No tienes cursos con ese nombre."
                                } else {
                                    "No te has inscrito a ningún curso."
                                }
                            )
                        }
                    } else {
                        LazyColumn(
                            modifier = Modifier.fillMaxSize(),
                            contentPadding = PaddingValues(bottom = 24.dp, top = 8.dp),
                            verticalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            items(filteredCourses) { course ->
                                val nombreDocente = uiState.teacherNames[course.teacherId] ?: "Cargando..."

                                CursoCard(
                                    titulo = course.name,
                                    docente = nombreDocente,
                                    categoria = course.category,
                                    onClick = {
                                        navController.navigate(Screen.MyCourseDetails.courseSelected(course.id))
                                    }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun CursoCard(
    titulo: String,
    docente: String,
    categoria: String,
    onClick: () -> Unit
) {
    Surface(
        shape = RoundedCornerShape(16.dp),
        color = Color(0xFFFFEB3B),
        shadowElevation = 6.dp,
        modifier = Modifier.fillMaxWidth(),
        onClick = onClick
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Surface(
                shape = RoundedCornerShape(8.dp),
                color = Color.Black.copy(alpha = 0.1f),
                modifier = Modifier.wrapContentSize()
            ) {
                Text(
                    text = categoria.uppercase(),
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextColorDark,
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = titulo,
                fontSize = 20.sp,
                fontWeight = FontWeight.ExtraBold,
                color = TextColorDark,
                lineHeight = 24.sp
            )

            Spacer(modifier = Modifier.height(12.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.Person, contentDescription = "Docente")
                Spacer(modifier = Modifier.size(5.dp))
                Text(
                    text = "Docente: ",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = TextColorDark.copy(alpha = 0.7f)
                )
                Text(
                    text = docente,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextColorDark
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MiscursosScreenPreview() {
    MaterialTheme {
       // MyCoursesScreen()
    }
}






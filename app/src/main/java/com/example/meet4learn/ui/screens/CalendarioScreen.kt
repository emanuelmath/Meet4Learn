package com.example.meet4learn.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.meet4learn.R

@Composable
fun CalendarioScreen() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {


        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF001A42))
                .padding(vertical = 12.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            Image(
                painter = painterResource(id = R.drawable.logoprincipal),
                contentDescription = "Logo",
                modifier = Modifier
                    .height(40.dp)
                    .padding(start = 16.dp),
                contentScale = ContentScale.Fit
            )
        }

        Spacer(modifier = Modifier.height(20.dp))


        Card(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(22.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFFFEB3B))
        ) {

            Column(
                modifier = Modifier.padding(18.dp)
            ) {


                Text(
                    text = "Noviembre, 2025",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )

                Spacer(modifier = Modifier.height(20.dp))

                //El calendario esta dibujado osea estatico
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()
                ) {


                    Row(
                        Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        listOf("D", "L", "M", "X", "J", "V", "S").forEach {
                            Text(
                                text = it,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Medium,
                                color = Color(0xFF444444)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))


                    val filas = listOf(
                        listOf("", "1", "2", "3", "4", "5", "6"),
                        listOf("7", "8", "9", "10", "11", "12", "13"),
                        listOf("14", "15", "16", "17", "18", "19", "20"),
                        listOf("21", "22", "23", "24", "25", "26", "27"),
                        listOf("28", "29", "30", "", "", "", "")
                    )

                    filas.forEach { fila ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp),
                            horizontalArrangement = Arrangement.SpaceAround
                        ) {
                            fila.forEach { dia ->

                                val isBlueDay = dia == "18"
                                val isRedDay = dia == "27"

                                Box(
                                    modifier = Modifier
                                        .size(38.dp)
                                        .background(
                                            when {
                                                isBlueDay -> Color(0xFF5AA9FF)
                                                isRedDay -> Color(0xFFD62828)
                                                else -> Color.Transparent
                                            },
                                            CircleShape
                                        ),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = dia,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 15.sp,
                                        color = if (dia.isNotEmpty()) Color.Black else Color.Transparent
                                    )
                                }
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))


                    Box(
                        modifier = Modifier
                            .height(5.dp)
                            .width(60.dp)
                            .background(Color.Black, RoundedCornerShape(50))
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Column(modifier = Modifier.padding(horizontal = 20.dp)) {


            Text(
                "18 DE NOVIEMBRE 2025",
                fontWeight = FontWeight.Bold,
                fontSize = 13.sp,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(8.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(10.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFF5AA9FF))
            ) {
                Row(
                    Modifier.padding(12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column {
                        Text("Clase de Álgebra Básica", color = Color.White, fontWeight = FontWeight.Bold)
                        Text("Suscripción", color = Color.White, fontSize = 12.sp)
                    }
                    Text("8:00 am - 12:00pm", color = Color.White)
                }
            }

            Spacer(modifier = Modifier.height(20.dp))


            Text(
                "27 DE NOVIEMBRE 2025",
                fontWeight = FontWeight.Bold,
                fontSize = 13.sp,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(8.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(10.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFD62828))
            ) {
                Row(
                    Modifier.padding(12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column {
                        Text("Evaluación Introducción a la cocina", color = Color.White, fontWeight = FontWeight.Bold)
                        Text("Suscripción", color = Color.White, fontSize = 12.sp)
                    }
                    Text("1:00 pm - 5:00pm", color = Color.White)
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun CalendarScreenPreview() {
    MaterialTheme {
        CalendarioScreen()
    }
}

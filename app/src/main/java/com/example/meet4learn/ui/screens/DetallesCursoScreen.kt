package com.example.meet4learn.ui.screens

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
import androidx.compose.material.icons.filled.Call
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun DetallesCursoScreen(onBack: () -> Unit = {}) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)

    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(BluePrimary)
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
        Spacer(modifier = Modifier.height(12.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .weight(1f)
        ) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFF9FD5FF)
                ),
                elevation = CardDefaults.cardElevation(6.dp)
            ) {
                Column(modifier = Modifier.padding(20.dp)) {
                    Text(
                        text = "INTRODUCCIÓN A LA COCINA",
                        fontWeight = FontWeight.Black,
                        fontSize = 18.sp,
                        color = Color(0xFF0D1B2A)
                    )
                    Text(
                        text = "DOCENTE: ANA GÓMEZ",
                        fontSize = 14.sp,
                        color = Color(0xFF0D1B2A)
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(14.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFF1E56A0)
                ),
                elevation = CardDefaults.cardElevation(8.dp)
            ) {
                Column(
                    modifier = Modifier
                        .padding(20.dp)
                ) {
                    Text(
                        text = "CLASE PROGRAMADA:",
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = "LUNES 10 / NOV",
                        color = Color.White,
                        fontWeight = FontWeight.Black,
                        fontSize = 16.sp
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                }


                Button(
                    onClick = {  },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF0A2C52)
                    ),
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier.align(Alignment.End)
                ) {
                    Icon(
                        imageVector = Icons.Default.Call,
                        contentDescription = "Unirse",
                        tint = Color.White
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text("UNIRSE", color = Color.White)
                }
            }

            Spacer(modifier = Modifier.height(20.dp))


            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(14.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFF7DB9FF)
                ),
                elevation = CardDefaults.cardElevation(6.dp)
            ) {

                Column(modifier = Modifier.padding(20.dp)) {

                    Text(
                        text = "MODULOS",
                        fontWeight = FontWeight.Black,
                        color = Color(0xFF0D1B2A)
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    ModuleItem("Fundamentos del Arte Culinario")
                    ModuleItem("Ingredientes y Sabores del Mundo")
                    ModuleItem("Preparaciones Básicas")
                }
            }

            Spacer(modifier = Modifier.height(28.dp))

            Button(
                onClick = onBack,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .width(180.dp)
                    .height(48.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF1E56A0)
                ),
                shape = RoundedCornerShape(12.dp),
                elevation = ButtonDefaults.buttonElevation(8.dp)
            ) {

                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Volver",
                    tint = Color.White
                )

                Spacer(modifier = Modifier.width(10.dp))

                Text(
                    text = "VOLVER",
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
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
        DetallesCursoScreen()
    }
}

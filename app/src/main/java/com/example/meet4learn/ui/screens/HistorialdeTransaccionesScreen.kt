package com.example.meet4learn.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
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
fun HistorialTransaccionesScreen(
    onVolver: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFEFEFEF)),
        horizontalAlignment = Alignment.CenterHorizontally
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

        Spacer(modifier = Modifier.height(20.dp))


        Card(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFF0A2B6D))
        ) {

            Column(
                modifier = Modifier.padding(20.dp)
            ) {

                Text(
                    text = "HISTORIAL DE TRANSACCIONES",
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(20.dp))

                TransactionItem(
                    titulo = "Introducción a la Cocina",
                    subtitulo = "Suscripción",
                    monto = "-$ 25.00",
                    colorMonto = Color(0xFFD62828)
                )

                Spacer(modifier = Modifier.height(12.dp))


                TransactionItem(
                    titulo = "Álgebra Básica",
                    subtitulo = "Suscripción",
                    monto = "$ 0.00",
                    colorMonto = Color(0xFF14A44D)
                )

                Spacer(modifier = Modifier.height(30.dp))



            }
        }
        Button(
            onClick = onVolver,
            modifier = Modifier
                .width(200.dp)
                .height(48.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF5AA9FF))
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Volver",
                    tint = Color.White
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = "Volver",
                    color = Color.White,
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
fun TransactionItem(
    titulo: String,
    subtitulo: String,
    monto: String,
    colorMonto: Color
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF103B87), RoundedCornerShape(10.dp))
            .padding(12.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Column {
                Text(
                    text = titulo,
                    color = Color.White,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = subtitulo,
                    color = Color.White.copy(alpha = 0.8f),
                    fontSize = 13.sp
                )
            }

            Box(
                modifier = Modifier
                    .background(colorMonto, RoundedCornerShape(10.dp))
                    .padding(vertical = 6.dp, horizontal = 12.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = monto,
                    color = Color.White,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HistorialdeTransaccionesScreenPreview() {
    MaterialTheme {
        HistorialTransaccionesScreen ()
    }
}

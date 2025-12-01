package com.example.meet4learn.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.meet4learn.R
import androidx.compose.ui.tooling.preview.Preview
import com.example.meet4learn.ui.theme.BluePrimary

@Composable
fun PagoScreen() {

    var expanded by remember { mutableStateOf(false) }
    val tarjetas = listOf("****1234-VISA", "****9981-MASTER", "Agregar nueva tarjeta")
    var tarjetaSeleccionada by remember { mutableStateOf(tarjetas[0]) }

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
                .fillMaxWidth()
                .heightIn(min = 420.dp),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFF78A9FF))
        ) {

            Column(
                modifier = Modifier
                    .padding(20.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    text = "REALIZAR PAGO",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )

                Text(
                    text = "$ 25.00",
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier.padding(top = 8.dp)
                )

                Spacer(modifier = Modifier.height(20.dp))


                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFF0A2B6D))
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {

                        Text("NOMBRE DEL CURSO:", color = Color(0xFFB6C4FF), fontWeight = FontWeight.SemiBold)
                        Text("INTRODUCCIÓN A LA COCINA", color = Color.White, fontWeight = FontWeight.Bold)

                        Spacer(modifier = Modifier.height(10.dp))

                        Text("DOCENTE:", color = Color(0xFFB6C4FF), fontWeight = FontWeight.SemiBold)
                        Text("ANA GÓMEZ", color = Color.White, fontWeight = FontWeight.Bold)

                        Spacer(modifier = Modifier.height(10.dp))

                        Text("HORARIO:", color = Color(0xFFB6C4FF), fontWeight = FontWeight.SemiBold)

                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 6.dp)
                                .height(30.dp)
                                .background(Color(0xFF0E1427), RoundedCornerShape(50)),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "1:00pm - 5:00pm",
                                color = Color.White,
                                fontWeight = FontWeight.Medium,
                                fontSize = 13.sp
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(25.dp))


                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(55.dp)
                ) {

                    OutlinedButton(
                        onClick = { expanded = true },
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.outlinedButtonColors(
                            containerColor = Color.White
                        ),
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = tarjetaSeleccionada,
                                color = Color.Gray,
                                fontSize = 14.sp
                            )
                            Text("▾", color = Color.DarkGray)
                        }
                    }

                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        tarjetas.forEach { opcion ->
                            DropdownMenuItem(
                                text = { Text(opcion) },
                                onClick = {
                                    tarjetaSeleccionada = opcion
                                    expanded = false
                                }
                            )
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(35.dp))


        Button(
            onClick = {  },
            modifier = Modifier
                .width(200.dp)
                .height(55.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF48C000))
        ) {
            Text(
                text = "CONFIRMAR",
                fontSize = 18.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PagoScreenPreview() {
    MaterialTheme {
       PagoScreen()
    }
}
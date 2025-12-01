package com.example.meet4learn.ui.screens

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
import androidx.compose.ui.tooling.preview.Preview
import com.example.meet4learn.ui.theme.BluePrimary

@Composable
fun DescripcionCursosScreen(onBack: () -> Unit = {}, onInscribirme: () -> Unit = {}) {

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

        Spacer(modifier = Modifier.height(16.dp))


        Column(
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .verticalScroll(rememberScrollState())
        ) {

            Surface(
                shape = RoundedCornerShape(16.dp),
                color = Color(0xFF1E40AF),
                shadowElevation = 12.dp
            ) {

                Column(
                    modifier = Modifier.padding(20.dp)
                ) {


                    Text(
                        "NOMBRE DEL CURSO:",
                        color = Color.White,
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 16.sp
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color(0xFF60A5FA), RoundedCornerShape(10.dp))
                            .padding(14.dp)
                    ) {
                        Text(
                            "INTRODUCCIÓN A LA COCINA",
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp
                        )
                    }

                    Spacer(modifier = Modifier.height(18.dp))


                    Text(
                        "DOCENTE:",
                        color = Color.White,
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 16.sp
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color(0xFF93C5FD), RoundedCornerShape(10.dp))
                            .padding(14.dp)
                    ) {
                        Text(
                            "ANA GÓMEZ",
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp
                        )
                    }

                    Spacer(modifier = Modifier.height(20.dp))


                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color(0xFF0F172A), RoundedCornerShape(12.dp))
                            .padding(16.dp)
                    ) {
                        Column {
                            Text(
                                "DESCRIPCIÓN:",
                                color = Color.White,
                                fontWeight = FontWeight.ExtraBold,
                                fontSize = 16.sp
                            )

                            Spacer(modifier = Modifier.height(6.dp))

                            Text(
                                "Curso introductorio de cocina orientado a desarrollar competencias básicas en manipulación de alimentos, técnicas de corte y preparación de platos sencillos.",
                                color = Color.White,
                                fontSize = 14.sp
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(20.dp))


                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            "HORARIO:",
                            color = Color.White,
                            fontWeight = FontWeight.ExtraBold,
                            fontSize = 16.sp
                        )

                        Spacer(modifier = Modifier.width(16.dp))

                        Text(
                            "1:00pm - 5:00pm",
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp
                        )
                    }

                    Spacer(modifier = Modifier.height(20.dp))


                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            "PRECIO:",
                            color = Color.White,
                            fontWeight = FontWeight.ExtraBold,
                            fontSize = 16.sp
                        )

                        Spacer(modifier = Modifier.width(20.dp))

                        Box(
                            modifier = Modifier
                                .background(Color(0xFFFFE4E6), RoundedCornerShape(10.dp))
                                .padding(horizontal = 22.dp, vertical = 10.dp)
                        ) {
                            Text(
                                "$ 25.00",
                                color = Color.Black,
                                fontWeight = FontWeight.ExtraBold,
                                fontSize = 20.sp
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(24.dp))
                }
            }

            Spacer(modifier = Modifier.height(28.dp))
            

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Button(
                    onClick = onBack,
                    colors = ButtonDefaults.buttonColors(Color(0xFF1E3A8A)),
                    shape = RoundedCornerShape(10.dp),
                    elevation = ButtonDefaults.buttonElevation(8.dp),
                    modifier = Modifier
                        .weight(1f)
                        .height(50.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = null,
                        tint = Color.White
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        "VOLVER",
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(modifier = Modifier.width(16.dp))

                Button(
                    onClick = onInscribirme,
                    colors = ButtonDefaults.buttonColors(Color(0xFF22C55E)),
                    shape = RoundedCornerShape(10.dp),
                    elevation = ButtonDefaults.buttonElevation(8.dp),
                    modifier = Modifier
                        .weight(1f)
                        .height(50.dp)
                ) {
                    Text(
                        "INSCRIBIRME",
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Spacer(modifier = Modifier.height(26.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DescripcionCursosScreenPreview() {
    MaterialTheme {
        DescripcionCursosScreen()
    }
}

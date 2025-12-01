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
import com.example.meet4learn.ui.theme.BluePrimary


@Composable
fun TarjetaScreen() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
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

        Spacer(modifier = Modifier.height(16.dp))


        Surface(
            shape = RoundedCornerShape(20.dp),
            color = Color(0xFF001645),
            shadowElevation = 12.dp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp)
        ) {

            Column(
                modifier = Modifier
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    text = "Información de la Tarjeta",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )

                Spacer(modifier = Modifier.height(12.dp))

                Image(
                    painter = painterResource(id = R.drawable.tarjeta),
                    contentDescription = null,
                    modifier = Modifier
                        .height(150.dp)
                        .width(150.dp)
                )

                Spacer(modifier = Modifier.height(4.dp))

                Image(
                    painter = painterResource(id = R.drawable.logovisa),
                    contentDescription = null,
                    modifier = Modifier
                        .height(100.dp)
                        .width(100.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))



                TarjetaInput(label = "Nombre del titular")

                Spacer(modifier = Modifier.height(16.dp))

                TarjetaInput(label = "Número de tarjeta")

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    TarjetaInput(
                        label = "Fecha de Vencimiento",
                        modifier = Modifier.weight(1f)
                    )

                    Spacer(modifier = Modifier.width(16.dp))

                    TarjetaInput(
                        label = "CVV",
                        modifier = Modifier.weight(0.7f)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(32.dp))



            Button(
                onClick = { },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF34C759)),
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .width(200.dp)
                    .height(52.dp),
                elevation = ButtonDefaults.buttonElevation(8.dp)
            ) {
                Text(
                    text = "GUARDAR",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }

            Spacer(modifier = Modifier.height(16.dp))



            Button(
                onClick = { },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1E3A8A)),
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .width(200.dp)
                    .height(52.dp),
                elevation = ButtonDefaults.buttonElevation(8.dp)
            ) {

                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Volver",
                    tint = Color.White
                )

                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = "VOLVER",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
        }
    }


@Composable
fun TarjetaInput(label: String, modifier: Modifier = Modifier) {

    OutlinedTextField(
        value = "",
        onValueChange = { },
        label = {
            Text(text = label, color = Color.White)
        },
        modifier = modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Color.White,
            unfocusedBorderColor = Color.White,
            focusedLabelColor = Color.White,
            unfocusedLabelColor = Color.White,
            focusedTextColor = Color.White,
            unfocusedTextColor = Color.White,
            cursorColor = Color.White
        )
    )
}

@Preview(showBackground = true)
@Composable
fun TarjetaScreenPreview() {
    MaterialTheme {
        TarjetaScreen()
    }
}

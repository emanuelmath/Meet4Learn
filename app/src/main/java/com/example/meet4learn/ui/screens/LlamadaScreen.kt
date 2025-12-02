package com.example.meet4learn.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.input.TextFieldValue
import com.example.meet4learn.R
import androidx.compose.ui.tooling.preview.Preview



@Composable
fun LlamadaScreen() {

    var message by remember { mutableStateOf(TextFieldValue("")) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF051336))
            .padding(16.dp)
    ) {


        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top
        ) {
            Image(
                painter = painterResource(id = R.drawable.logoprincipal),
                contentDescription = null,
                modifier = Modifier.size(48.dp)
            )

            Box(
                modifier = Modifier
                    .size(50.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color(0xFFD6242A)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "Salir",
                    tint = Color.White
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))


        Text(
            text = "INTRODUCCIÓN A LA COCINA",
            color = Color.White,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(20.dp))


        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(220.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(Color(0xFF000B26)),
            contentAlignment = Alignment.Center
        ) {


        }

        Spacer(modifier = Modifier.height(24.dp))


        Text(
            text = "CHAT EN VIVO",
            color = Color.White,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(12.dp))


        Column(modifier = Modifier.fillMaxWidth()) {

            ChatBubble(
                name = "Julio Vanegas",
                message = "Muy buena clase chef"
            )

            Spacer(modifier = Modifier.height(10.dp))

            ChatBubble(
                name = "Sarai Romero",
                message = "Entendido! Chef"
            )

            Spacer(modifier = Modifier.height(10.dp))

            ChatBubble(
                name = "Tú",
                message = "Entendido! Chef",
                isOwn = true
            )
        }

        Spacer(modifier = Modifier.weight(1f))


        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(50.dp))
                .background(Color(0xFF0A1B48))
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            BasicTextField(
                value = message,
                onValueChange = { message = it },
                modifier = Modifier.weight(1f),
                textStyle = TextStyle(color = Color.White, fontSize = 14.sp),
                decorationBox = { innerTextField ->
                    if (message.text.isEmpty()) {
                        Text(
                            "envía un mensaje...",
                            color = Color.Gray,
                            fontSize = 14.sp
                        )
                    }
                    innerTextField()
                }
            )

            Spacer(modifier = Modifier.width(10.dp))

            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(Color(0xFF325FFF)),
                contentAlignment = Alignment.Center
            ) {
                Text("➤", color = Color.White, fontSize = 22.sp)
            }
        }
    }
}


@Composable
fun ChatBubble(name: String, message: String, isOwn: Boolean = false) {

    val bubbleColor = if (isOwn) Color(0xFF6CA8FF) else Color(0xFF325FFF)

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = if (isOwn) Alignment.End else Alignment.Start
    ) {

        Text(
            text = name,
            color = Color.White,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(4.dp))

        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
                .background(bubbleColor)
                .padding(horizontal = 12.dp, vertical = 8.dp)
        ) {
            Text(message, color = Color.White, fontSize = 14.sp)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LlamadaScreenPreview() {
    MaterialTheme {
        LlamadaScreen()
    }
}
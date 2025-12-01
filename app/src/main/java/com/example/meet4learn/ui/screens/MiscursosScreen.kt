package com.example.meet4learn.ui.screens


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
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
import com.example.meet4learn.ui.theme.BluePrimary
import com.example.meet4learn.ui.theme.TextColorDark

@Composable
fun MisCursosScreen() {

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


            CursoCard(
                titulo = "Introducción a la Cocina",
                docente = "Ana Gómez"
            )

            Spacer(modifier = Modifier.height(16.dp))

            CursoCard(
                titulo = "Álgebra Básica",
                docente = "Rodrigo Contreras"
            )
        }



    }
}



@Composable
fun CursoCard(titulo: String, docente: String) {

    Surface(
        shape = RoundedCornerShape(16.dp),
        color = Color(0xFFFFEB3B),
        shadowElevation = 8.dp,
        modifier = Modifier.fillMaxWidth()
    ) {

        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Column {
                Text(
                    text = titulo,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextColorDark
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "Docente: $docente",
                    fontSize = 14.sp,
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
        MisCursosScreen()
    }
}






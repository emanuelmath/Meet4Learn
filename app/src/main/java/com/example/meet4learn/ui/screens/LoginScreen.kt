package com.example.meet4learn.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.meet4learn.ui.navigation.Screen
import com.example.meet4learn.ui.viewmodels.LoginViewModel
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import com.example.meet4learn.R
import com.example.meet4learn.ui.theme.*
import com.example.meet4learn.ui.components.CustomOutlinedTextField
import com.example.meet4learn.ui.components.LoginButton

//@Composable
//fun LoginScreen(navController: NavController, loginViewModel: LoginViewModel) {
//    val uiState = loginViewModel.uiState
//    var email by remember { mutableStateOf("") }
//    var password by remember { mutableStateOf("") }
//
//    LaunchedEffect(uiState.isSuccess) {
//        if (uiState.isSuccess) {
//            navController.navigate(Screen.Dashboard.route) {
//                popUpTo(Screen.Login.route) { inclusive = true }
//            }
//            loginViewModel.resetState()
//        }
//    }
//
//    val scrollState = rememberScrollState()
//    Column(
//        horizontalAlignment = Alignment.CenterHorizontally,
//        modifier = Modifier.padding(top = 50.dp).verticalScroll(scrollState),
//
//    ) {
//        OutlinedTextField(
//            value = email,
//            onValueChange = {email = it},
//            label = {Text("Correo Electrónico")},
//            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
//            colors = OutlinedTextFieldDefaults.colors(
//                focusedBorderColor = Color.White,
//                unfocusedBorderColor = Color.LightGray,
//                cursorColor = Color.White,
//                focusedLabelColor = Color.White,
//                unfocusedLabelColor = Color.Gray,
//                focusedTextColor = Color.Black,
//                unfocusedTextColor = Color.Black,
//                focusedContainerColor = Color.White,
//                unfocusedContainerColor = Color.White
//            ),
//            singleLine = true
//        )
//
//        OutlinedTextField(
//            value = password,
//            onValueChange = {password = it},
//            label = {Text("Contraseña")},
//            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
//            visualTransformation = PasswordVisualTransformation(),
//            colors = OutlinedTextFieldDefaults.colors(
//                focusedBorderColor = Color.White,
//                unfocusedBorderColor = Color.LightGray,
//                cursorColor = Color.White,
//                focusedLabelColor = Color.White,
//                unfocusedLabelColor = Color.Gray,
//                focusedTextColor = Color.Black,
//                unfocusedTextColor = Color.Black,
//                focusedContainerColor = Color.White,
//                unfocusedContainerColor = Color.White
//            ),
//            singleLine = true
//        )
//
//        if (uiState.errorMessage != null) {
//            Text(
//                text = uiState.errorMessage,
//                color = Color.Red,
//            )
//            Spacer(modifier = Modifier.height(8.dp))
//            Button(onClick = {
//                loginViewModel.resetState()
//                email = ""
//                password = ""
//            }) { Text("Limpiar") }
//        }
//
//        if (uiState.isLoading) {
//            CircularProgressIndicator()
//        } else {
//            Button(
//                onClick = {
//                    if (email.isNotBlank() && password.isNotBlank()) {
//                        loginViewModel.loginUser(email, password)
//                    }
//                },
//                modifier = Modifier.fillMaxWidth().height(50.dp),
//                enabled = email.isNotBlank() && password.isNotBlank()
//            ) {
//                Text("Ingresar")
//            }
//        }
//
//        Spacer(modifier = Modifier.size(25.dp))
//
//        Button(onClick = {
//            navController.navigate(Screen.Register.route) {
//                popUpTo(Screen.Login.route) { inclusive = true }
//            }
//        }) { Text("Registrarse")}
//    }
//}
//
//
//

@Composable
fun LoginScreen(navController: NavController, loginViewModel: LoginViewModel) {
    val uiState = loginViewModel.uiState
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    LaunchedEffect(uiState.isSuccess) {
        if (uiState.isSuccess) {
            navController.navigate(Screen.Main.route) {
                popUpTo(Screen.Login.route) { inclusive = true }
            }
            loginViewModel.resetState()
        }
    }
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = 32.dp)
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Image(

            painter = painterResource(R.drawable.logoprincipal),
            contentDescription = "Logo de Meet4Learn",
            modifier = Modifier
                .size(300.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "¡Conectado con el conocimiento!",
            color = TextColorDark,
            fontSize = 14.sp
        )
        Text(
            text = "Bienvenido(a) de nuevo",
            color = TextColorDark,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium
        )

        Spacer(modifier = Modifier.height(32.dp))


        CustomOutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = "Correo electrónico",
            keyboardType = KeyboardType.Email
        )

        Spacer(modifier = Modifier.height(16.dp))


        CustomOutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = "Contraseña",
            keyboardType = KeyboardType.Password
        )

        Spacer(modifier = Modifier.height(48.dp))

        if (uiState.errorMessage != null) {
            Text(
                text = uiState.errorMessage,
                color = Color.Red,
            )
            Spacer(modifier = Modifier.height(8.dp))
            Button(onClick = {
                loginViewModel.resetState()
                email = ""
                password = ""
            }) { Text("Limpiar") }
        }

        if (uiState.isLoading) {
            CircularProgressIndicator()
        } else {
            LoginButton(onClick = {
                if (email.isNotBlank() && password.isNotBlank()) {
                    loginViewModel.loginUser(email, password)
                }
            }, enabled = email.isNotBlank() && password.isNotBlank())
        }


        Spacer(modifier = Modifier.height(48.dp))

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "¿No tienes una cuenta?",
                color = TextColorDark,
                fontSize = 14.sp
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "CREAR CUENTA",
                color = TextColorDark,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                textDecoration = TextDecoration.Underline,
                modifier = Modifier.clickable {
                    navController.navigate(Screen.Register.route) {
                        popUpTo(Screen.Login.route) { inclusive = true }
                    }
                }
            )
        }
    }
}



@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    MaterialTheme {
       // LoginScreen2()
    }
}
package com.example.meet4learn.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.meet4learn.R
import com.example.meet4learn.ui.theme.*
import com.example.meet4learn.ui.viewmodels.StudentProfileViewModel


@Composable
fun TarjetaScreen(
    viewModel: StudentProfileViewModel,
    onDismiss: () -> Unit
) {
    val uiState = viewModel.uiState

    Surface(
        shape = RoundedCornerShape(20.dp),
        color = Color(0xFF001645),
        shadowElevation = 12.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(24.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = "Información de la Tarjeta",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(id = R.drawable.tarjeta),
                    contentDescription = null,
                    modifier = Modifier.size(100.dp)
                )
                Spacer(modifier = Modifier.width(16.dp))
                Image(
                    painter = painterResource(id = R.drawable.logovisa),
                    contentDescription = null,
                    modifier = Modifier.size(80.dp)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))


            TarjetaInput(
                label = "Nombre del titular",
                value = uiState.newCardName,
                onValueChange = { viewModel.updateCardInput(name = it) }
            )

            Spacer(modifier = Modifier.height(16.dp))

            TarjetaInput(
                label = "Número de tarjeta",
                value = uiState.newCardNumber,
                onValueChange = { viewModel.updateCardInput(number = it, conditionNumber = true) },
                keyboardType = KeyboardType.Number,
                //enabled =  uiState.newCardNumber.length <= 16
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                TarjetaInput(
                    label = "Vencimiento (MM/YY)",
                    value = uiState.newCardExpiry,
                    onValueChange = {
                        if (it.length == 2 && !uiState.newCardExpiry.contains("/")) {
                            viewModel.updateCardInput(expiry = "$it/")
                        } else {
                            viewModel.updateCardInput(expiry = it)
                        }
                    },
                    modifier = Modifier.weight(1f),
                    keyboardType = KeyboardType.Number
                )

                Spacer(modifier = Modifier.width(16.dp))

                TarjetaInput(
                    label = "CVV",
                    value = uiState.newCardCvv,
                    onValueChange = { viewModel.updateCardInput(cvv = it, conditionNumber = true) },
                    modifier = Modifier.weight(0.7f),
                    keyboardType = KeyboardType.Number
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = { viewModel.saveNewCard() },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF34C759)),
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                elevation = ButtonDefaults.buttonElevation(8.dp)
            ) {
                if (uiState.isLoading) {
                    CircularProgressIndicator(color = Color.White, modifier = Modifier.size(24.dp))
                } else {
                    Text(
                        text = "GUARDAR",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = onDismiss,
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1E3A8A)),
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                elevation = ButtonDefaults.buttonElevation(8.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "Cancelar",
                    tint = Color.White
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "CANCELAR",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
        }
    }
}


@Composable
fun TarjetaInput(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    keyboardType: KeyboardType = KeyboardType.Text,
    //enabled: Boolean = true
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(text = label, color = Color.White) },
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        //enabled = enabled,
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
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
       // TarjetaScreen()
    }
}

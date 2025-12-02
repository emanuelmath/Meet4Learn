package com.example.meet4learn.ui.screens

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.meet4learn.R
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.Dialog
import com.example.meet4learn.ui.theme.*
import com.example.meet4learn.ui.viewmodels.StudentProfileViewModel




@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UsuarioScreen(studentProfileViewModel: StudentProfileViewModel) {

    val uiState = studentProfileViewModel.uiState
    val context = LocalContext.current
    var cardsExpanded by remember { mutableStateOf(false) }

    if (uiState.isAddCardDialogOpen) {
        Dialog(
            onDismissRequest = { studentProfileViewModel.toggleAddCardDialog(false) }
        ) {
            TarjetaScreen(
                viewModel = studentProfileViewModel,
                onDismiss = { studentProfileViewModel.toggleAddCardDialog(false) }
            )
        }
    }

    LaunchedEffect(uiState.errorMessage) {
        uiState.errorMessage?.let { error ->
            Toast.makeText(context, error, Toast.LENGTH_LONG).show()
            studentProfileViewModel.clearError()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {

        if (uiState.isLoading && !uiState.isRefresh) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        } else {
            PullToRefreshBox(
                isRefreshing = uiState.isRefresh,
                onRefresh = { studentProfileViewModel.getMyData(isRefresh = true) },
                modifier = Modifier.fillMaxSize()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                        .padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Surface(
                        shape = RoundedCornerShape(20.dp),
                        color = Color(0xFF1E3A8A),
                        shadowElevation = 12.dp,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(
                            modifier = Modifier.padding(20.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "HOLA, ${uiState.profileStudent?.fullName ?: "CARGANDO"}",
                                color = Color.White,
                                fontWeight = FontWeight.ExtraBold,
                                fontSize = 26.sp
                            )
                            Spacer(modifier = Modifier.height(16.dp))

                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(Color(0xFF60A5FA), RoundedCornerShape(12.dp))
                                    .padding(16.dp)
                            ) {
                                Column {
                                    Text("NOMBRE COMPLETO:", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 14.sp)
                                    Text(uiState.profileStudent?.fullName?.uppercase() ?: "CARGANDO", color = Color.White, fontSize = 15.sp)
                                    Spacer(modifier = Modifier.height(12.dp))
                                    Text("CORREO ELECTRÓNICO:", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 14.sp)
                                    Text(text = uiState.email, color = Color.White, fontSize = 15.sp)
                                }
                                IconButton(
                                    onClick = { },
                                    modifier = Modifier.align(Alignment.BottomEnd).size(38.dp).background(Color(0xFF16A34A), CircleShape)
                                ) {
                                    Icon(Icons.Default.Edit, contentDescription = "Editar", tint = Color.White)
                                }
                            }
                            Spacer(modifier = Modifier.height(16.dp))
                            Button(
                                onClick = { },
                                modifier = Modifier.fillMaxWidth().height(48.dp),
                                colors = ButtonDefaults.buttonColors(Color(0xFFB91C1C)),
                                shape = RoundedCornerShape(12.dp),
                                elevation = ButtonDefaults.buttonElevation(8.dp)
                            ) {
                                Text("Cambiar Contraseña", color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.Bold)
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Surface(
                            shape = RoundedCornerShape(12.dp),
                            color = Color(0xFF93C5FD),
                            shadowElevation = 8.dp,
                            modifier = Modifier.weight(1f)
                        ) {
                            Column(modifier = Modifier.padding(16.dp)) {
                                Text("Saldo Actual", color = Color(0xFF1E3A8A), fontWeight = FontWeight.Bold)
                                Text(
                                    text = "%.2f".format(uiState.profileStudent?.balance ?: 0.00),
                                    color = Color(0xFF1E3A8A), fontWeight = FontWeight.Bold, fontSize = 26.sp
                                )
                            }
                        }
                        Spacer(modifier = Modifier.width(16.dp))
                        Surface(
                            shape = RoundedCornerShape(12.dp),
                            color = Color(0xFF0F172A),
                            shadowElevation = 8.dp,
                            modifier = Modifier.weight(1f)
                        ) {
                            Column(
                                modifier = Modifier.padding(16.dp),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text("Transacciones", color = Color.White, fontWeight = FontWeight.Bold)
                                Spacer(modifier = Modifier.height(6.dp))
                                Button(
                                    onClick = {},
                                    shape = RoundedCornerShape(8.dp),
                                    colors = ButtonDefaults.buttonColors(Color(0xFF3B82F6)),
                                    modifier = Modifier.height(36.dp)
                                ) {
                                    Text("Ver Historial", color = Color.White, fontWeight = FontWeight.Bold)
                                }
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    Surface(
                        shape = RoundedCornerShape(16.dp),
                        color = Color(0xFF1D4ED8),
                        shadowElevation = 10.dp,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(modifier = Modifier.padding(20.dp)) {

                            Text(
                                text = "Tus tarjetas",
                                color = Color.White,
                                fontWeight = FontWeight.Bold,
                                fontSize = 18.sp
                            )

                            Spacer(modifier = Modifier.height(16.dp))

                            Surface(
                                shape = RoundedCornerShape(10.dp),
                                color = Color(0xFF60A5FA),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable { cardsExpanded = !cardsExpanded }
                                    .animateContentSize()
                            ) {
                                Column {
                                    Row(
                                        modifier = Modifier.padding(12.dp),
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        val headerText = if (uiState.cards.isEmpty()) "Sin tarjetas guardadas"
                                        else if (cardsExpanded) "Ocultar detalles"
                                        else "**** ${uiState.cards.first().lastFourDigits} - ${uiState.cards.first().brand}"

                                        Text(headerText, color = Color.White, modifier = Modifier.weight(1f))

                                        Icon(
                                            imageVector = if (cardsExpanded) Icons.Default.KeyboardArrowUp else Icons.Default.ArrowDropDown,
                                            contentDescription = null,
                                            tint = Color.White
                                        )
                                    }

                                    if (cardsExpanded && uiState.cards.isNotEmpty()) {
                                        HorizontalDivider(color = Color.White.copy(alpha = 0.5f))
                                        uiState.cards.forEach { card ->
                                            Row(
                                                modifier = Modifier
                                                    .fillMaxWidth()
                                                    .padding(12.dp),
                                                verticalAlignment = Alignment.CenterVertically
                                            ) {
                                                Icon(Icons.Default.ShoppingCart, null, tint = Color.White)
                                                Spacer(modifier = Modifier.width(8.dp))
                                                Text("**** ${card.lastFourDigits} - ${card.brand}", color = Color.White)
                                            }
                                        }
                                    }
                                }
                            }

                            Spacer(modifier = Modifier.height(18.dp))

                            Button(
                                onClick = { studentProfileViewModel.toggleAddCardDialog(true) }, // Abre el modal
                                colors = ButtonDefaults.buttonColors(Color(0xFF93C5FD)),
                                modifier = Modifier.align(Alignment.End),
                                shape = RoundedCornerShape(10.dp),
                                elevation = ButtonDefaults.buttonElevation(6.dp)
                            ) {
                                Text(
                                    text = "AGREGAR TARJETA NUEVA",
                                    color = Color(0xFF1E3A8A),
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 12.sp
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(24.dp))
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun UsuarioScreenPreview() {
    MaterialTheme {
       // UsuarioScreen()
    }
}




package com.example.meet4learn.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.SegmentedButtonDefaults.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.meet4learn.domain.models.Module
import com.example.meet4learn.ui.utils.ClassJoinStatus
import com.example.meet4learn.ui.utils.DateUtils
import kotlinx.coroutines.delay
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter

@Composable
fun ModuleInfoDialog(
    module: Module,
    onDismiss: () -> Unit,
    onJoinClick: () -> Unit
) {
    var currentTime by remember { mutableStateOf(OffsetDateTime.now()) }

    LaunchedEffect(Unit) {
        while (true) {
            delay(1000)
            currentTime = OffsetDateTime.now()
        }
    }

    val isoString = module.scheduledAt.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME)
    val joinStatus = DateUtils.canJoinClass(module.status,
        startTimeIso = isoString, currentTime)


    Dialog(onDismissRequest = onDismiss) {
        Surface(
            shape = RoundedCornerShape(16.dp),
            color = Color.White,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = module.title,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF0D1B2A),
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(16.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.DateRange, contentDescription = null, tint = Color.Gray)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Inicia: ${DateUtils.formatTimeClass(isoString)}", fontSize = 16.sp, color = Color.Gray)
                }

                Spacer(modifier = Modifier.height(24.dp))

                when (joinStatus) {
                    ClassJoinStatus.Finished -> {
                        Text("La clase ha finalizado.", color = Color.Red, fontSize = 14.sp)
                    }
                    ClassJoinStatus.Expired -> {
                        Text("El tiempo de la clase ha expirado.", color = Color.Red, fontSize = 14.sp)
                    }
                    ClassJoinStatus.NotStartedYet -> {
                        Text("El botón se activará 10 minutos antes.", color = Color(0xFFECA626), fontSize = 12.sp)
                    }
                    ClassJoinStatus.Open -> {
                        Text("¡La clase está en vivo!", color = Color(0xFF22C55E), fontWeight = FontWeight.Bold)
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = onJoinClick,
                    enabled = joinStatus == ClassJoinStatus.Open,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF22C55E),
                        disabledContainerColor = Color.Gray
                    ),
                    modifier = Modifier.fillMaxWidth().height(48.dp),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Icon(Icons.Default.PlayArrow, null, tint = Color.White)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("UNIRSE A LA CLASE", color = Color.White, fontWeight = FontWeight.Bold)
                }

                Spacer(modifier = Modifier.height(12.dp))
                TextButton(onClick = onDismiss) { Text("Cerrar", color = Color(0xFF0D1B2A)) }
            }
        }
    }
}
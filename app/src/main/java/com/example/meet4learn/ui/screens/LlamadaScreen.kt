package com.example.meet4learn.ui.screens



import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import com.example.meet4learn.R
import com.example.meet4learn.ui.utils.DateUtils
import com.example.meet4learn.ui.viewmodels.VideoCallViewModel
import io.livekit.android.renderer.TextureViewRenderer
import livekit.org.webrtc.RendererCommon
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter

@Composable
fun LlamadaScreen(
    moduleId: Int,
    navController: NavController,
    videoCallViewModel: VideoCallViewModel
) {
    // Inicializar sesión.
    LaunchedEffect(moduleId) {
        videoCallViewModel.initSession(moduleId)
    }

    val uiState = videoCallViewModel.uiState
    var inputText by remember { mutableStateOf("") }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF051336))
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.logoprincipal),
                contentDescription = null,
                modifier = Modifier.size(48.dp)
            )

            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color(0xFFD6242A))
                    .clickable {
                        navController.popBackStack()
                    },
                contentAlignment = Alignment.Center
            ) {
                Icon(Icons.Default.Close, contentDescription = "Salir", tint = Color.White)
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(220.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(Color.Black),
            contentAlignment = Alignment.Center
        ) {
            if (uiState.teacherVideoTrack != null) {
                AndroidView(
                    factory = { context ->
                        TextureViewRenderer(context).apply {
                            videoCallViewModel.initVideoRenderer(this)
                            setScalingType(RendererCommon.ScalingType.SCALE_ASPECT_FIT)
                            setEnableHardwareScaler(true)
                        }
                    },
                    update = { renderer ->
                        val videoTrack = uiState.teacherVideoTrack
                        if (videoTrack != null) {
                            try {
                                videoTrack.removeRenderer(renderer)
                                videoTrack.addRenderer(renderer)
                            } catch (e: Exception) {
                                e.printStackTrace()
                            }
                        }
                    },
                    onRelease = { renderer ->
                        try {
                            uiState.teacherVideoTrack?.removeRenderer(renderer)
                            renderer.release()
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                    },
                    modifier = Modifier.fillMaxSize()
                )
            } else {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    if (uiState.isLoading) {
                        CircularProgressIndicator(color = Color.White)
                    } else {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = null,
                            tint = Color.Gray,
                            modifier = Modifier.size(40.dp)
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Esperando transmisión...",
                            color = Color.White.copy(alpha = 0.7f),
                            fontSize = 14.sp
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text("CHAT EN VIVO", color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold)

        if (uiState.errorMessage != null) {
            Text(uiState.errorMessage!!, color = Color.Red, fontSize = 12.sp)
        }

        Spacer(modifier = Modifier.height(12.dp))

        LazyColumn(
            modifier = Modifier.weight(1f),
            reverseLayout = false
        ) {
            items(uiState.messages) { msg ->
                val senderProfile = uiState.userProfiles[msg.senderId]
                val displayName = senderProfile?.fullName ?: "Cargando..."
                val isTeacher = senderProfile?.role?.equals("teacher", ignoreCase = true) == true

                ChatBubble(
                    name = if (msg.senderId == uiState.currentUserId) "Tú" else displayName,
                    message = msg.messageText,
                    time = msg.time,
                    isOwn = msg.senderId == uiState.currentUserId,
                    isTeacher = isTeacher
                )
                Spacer(modifier = Modifier.height(10.dp))
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(50.dp))
                .background(Color(0xFF0A1B48))
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            BasicTextField(
                value = inputText,
                onValueChange = { inputText = it },
                modifier = Modifier.weight(1f),
                textStyle = TextStyle(color = Color.White, fontSize = 14.sp),
                decorationBox = { innerTextField ->
                    if (inputText.isEmpty()) {
                        Text("Escribe un mensaje...", color = Color.Gray, fontSize = 14.sp)
                    }
                    innerTextField()
                }
            )

            Spacer(modifier = Modifier.width(10.dp))

            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(Color(0xFF325FFF))
                    .clickable {
                        if (inputText.isNotBlank()) {
                            videoCallViewModel.sendMessage(moduleId, inputText)
                            inputText = ""
                        }
                    },
                contentAlignment = Alignment.Center
            ) {
                Text("➤", color = Color.White, fontSize = 22.sp)
            }
        }
    }
}

@Composable
fun ChatBubble(name: String, message: String, time: OffsetDateTime?, isOwn: Boolean, isTeacher: Boolean) {
    val bubbleColor = when {
        isOwn -> Color(0xFF6CA8FF)
        isTeacher -> Color(0xFFD6242A)
        else -> Color(0xFF325FFF)
    }
    val timeStr = remember(time) { DateUtils.formatTimeMessage(time.toString()) }

    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = if (isOwn) Alignment.End else Alignment.Start) {
        Text(
            text = if(isTeacher && !isOwn) "$name (Docente)" else name,
            color = if (isTeacher) Color(0xFFFFCC00) else Color.LightGray,
            fontSize = 12.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(bottom = 4.dp)
        )
        Box(modifier = Modifier
            .clip(RoundedCornerShape(
                topStart = 10.dp,
                topEnd = 10.dp,
                bottomStart = if (isOwn) 10.dp else 0.dp,
                bottomEnd = if (isOwn) 0.dp else 10.dp
            ))
            .background(bubbleColor)
            .padding(horizontal = 12.dp, vertical = 8.dp)) {
            Column {
                Text(message, color = Color.White, fontSize = 14.sp)
                if (timeStr.isNotEmpty()) Text(text = timeStr, color = Color.White.copy(alpha = 0.7f), fontSize = 10.sp, modifier = Modifier.align(Alignment.End).padding(top = 2.dp))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LlamadaScreenPreview() {
    MaterialTheme {
        //LlamadaScreen()
    }
}
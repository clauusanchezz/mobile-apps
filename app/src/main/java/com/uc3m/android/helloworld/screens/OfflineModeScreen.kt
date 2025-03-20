package com.uc3m.android.helloworld.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController

@Composable
fun OfflineModeScreen(navController: NavController?) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFFFFFFFF) // Fondo blanco
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Spacer(modifier = Modifier.height(40.dp))

            // Título con color naranja claro
            Text(
                text = "Offline",
                fontSize = 36.sp,
                fontWeight = FontWeight.ExtraBold,
                color = Color(0xFFFF9966), // Naranja claro
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Lista de materias con emojis
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(top = 16.dp, bottom = 32.dp)
            ) {
                val subjectsWithIcons = listOf(
                    "📐 Maths" to "Maths",
                    "🗺️ Geography" to "Geography",
                    "📜 History" to "History",
                    "📖 English" to "English",
                    "🇪🇸 Spanish" to "Spanish",
                    "🎵 Music" to "Music",
                    "🧬 Biology" to "Biology",
                    "🔬 Physics" to "Physics",
                    "🎨 Art" to "Art"
                )

                items(subjectsWithIcons.size) { index ->
                    val (emojiSubject, subject) = subjectsWithIcons[index]
                    OfflineButton(subjectName = emojiSubject) {
                        // Manejo del click
                    }
                }
            }
        }
    }
}

@Composable
fun OfflineButton(subjectName: String, onClick: () -> Unit) {
    val naranjitafondo = Color(0xFFFF9966) // Naranja claro

    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp)
            .padding(horizontal = 8.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = naranjitafondo,
            contentColor = Color.White
        ),
        shape = RoundedCornerShape(20.dp),
    ) {
        Text(
            text = subjectName,
            fontSize = 22.sp,
            fontWeight = FontWeight.SemiBold,
            letterSpacing = 1.sp
        )
    }
}


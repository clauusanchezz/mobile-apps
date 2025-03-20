package com.uc3m.android.helloworld.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StudyProgressScreen(navController: NavController) {
    var showMenu by remember { mutableStateOf(false) }
    val naranjitafondo = Color(0xFFFF9966)
    val blackColor = Color(0xFF000000)

    // Progreso predefinido para cada asignatura
    val subjects = listOf(
        "Maths" to 0.7f,
        "Geography" to 0.5f,
        "History" to 0.3f,
        "English" to 0.8f,
        "Spanish" to 0.6f,
        "Music" to 0.4f,
        "Biology" to 0.9f,
        "Physics" to 0.2f,
        "Art" to 0.75f
    )

    Scaffold(
        topBar = {
            TopAppBar(
                //title = { Text("Study Progress") },
                title = {
                    Text(
                        "Study Progress",
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = naranjitafondo,
                    titleContentColor = blackColor
                ),
                actions = {
                    Box {
                        IconButton(onClick = { showMenu = true }) {
                            Icon(
                                imageVector = Icons.Default.MoreVert,
                                contentDescription = "More options",
                                tint = blackColor
                            )
                        }
                        DropdownMenu(
                            expanded = showMenu,
                            onDismissRequest = { showMenu = false }
                        ) {
                            DropdownMenuItem(
                                text = { Text("Grades") },
                                onClick = { showMenu = false }
                            )
                            DropdownMenuItem(
                                text = { Text("Statistics") },
                                onClick = { showMenu = false }
                            )
                            DropdownMenuItem(
                                text = { Text("Back to Home") },
                                onClick = { 
                                    showMenu = false
                                    navController.navigate("home") {
                                        popUpTo("study_progress") { inclusive = true }
                                    }
                                }
                            )
                        }
                    }
                }
            )
        }
    ) { paddingValues ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .background(naranjitafondo)
                .padding(paddingValues),
            color = naranjitafondo
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                Spacer(modifier = Modifier.height(20.dp))

                // LazyColumn para el desplazamiento
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(vertical = 16.dp, horizontal = 16.dp)
                ) {
                    items(subjects) { (subject, progress) ->
                        ProgressItem(subject = subject, progress = progress)
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                }
            }
        }
    }
}

@Composable
fun ProgressItem(subject: String, progress: Float) {
    // Contenedor de cada asignatura con el progreso
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Columna con el nombre y el progreso
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = subject,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(8.dp))
                LinearProgressIndicator(
                    progress = { progress },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(8.dp),
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "${(progress * 100).toInt()}% Completed",
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            }
        }
    }
}

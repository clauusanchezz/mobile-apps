package com.uc3m.android.helloworld.screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.uc3m.android.helloworld.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    val orangeColor = Color(0xFFfc4b08) // Naranja para los botones
    val whiteColor = Color(0xFFFFFFFF) // Blanco para el fondo
    val blackColor = Color(0xFF000000) // Negro para el título
    val naranjitafondo = Color(0xFFFF9966)
    val progressColor = Color(0xFFfc4b08)
    val backgroundColor = Color(0xFFFFE5D9)
    
    var showMenu by remember { mutableStateOf(false) }
    // This would come from your user's data
    val currentLevel = 2
    val progress = 0.75f // 75% progress to next level

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("StudyStorm") },
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
                                text = { Text("Profile Settings ") },
                                onClick = { 
                                    showMenu = false
                                    // TODO: Navigate to profile settings
                                }
                            )
                            DropdownMenuItem(
                                text = { Text("Notifications") },
                                onClick = { 
                                    showMenu = false
                                    // TODO: Navigate to notifications
                                }
                            )
                            DropdownMenuItem(
                                text = { Text("Help & Support") },
                                onClick = { 
                                    showMenu = false
                                    // TODO: Navigate to help
                                }
                            )
                            DropdownMenuItem(
                                text = { Text("About") },
                                onClick = { 
                                    showMenu = false
                                    // TODO: Navigate to about
                                }
                            )
                            DropdownMenuItem(
                                text = { Text("Logout") },
                                onClick = { 
                                    showMenu = false
                                    navController.navigate("login") {
                                        popUpTo("home") { inclusive = true }
                                    }
                                }
                            )
                        }
                    }
                }
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(naranjitafondo)
                .padding(paddingValues)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                // Level Progress Indicator
                Box(
                    modifier = Modifier
                        .size(250.dp)
                        .padding(top = 32.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Canvas(
                        modifier = Modifier
                            .size(200.dp)
                            .padding(16.dp)
                    ) {
                        // Background circle
                        drawArc(
                            color = backgroundColor,
                            startAngle = 120f,
                            sweepAngle = 300f,
                            useCenter = false,
                            style = Stroke(width = 40f, cap = StrokeCap.Round),
                            size = Size(size.width, size.height)
                        )
                        
                        // Progress arc
                        drawArc(
                            color = progressColor,
                            startAngle = 120f,
                            sweepAngle = 300f * progress,
                            useCenter = false,
                            style = Stroke(width = 40f, cap = StrokeCap.Round),
                            size = Size(size.width, size.height)
                        )
                    }
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Level",
                            fontSize = 24.sp,
                            color = blackColor,
                            fontWeight = FontWeight.Normal
                        )
                        Text(
                            text = currentLevel.toString(),
                            fontSize = 48.sp,
                            color = blackColor,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

                Spacer(modifier = Modifier.height(32.dp))
                Text(
                    text = "Welcome to StudyStorm!",
                    fontSize = 28.sp,
                    color = blackColor,
                    modifier = Modifier.padding(top = 12.dp)
                )
                Text(
                    text = "Select an option:",
                    fontSize = 18.sp,
                    color = blackColor,
                    modifier = Modifier.padding(top = 12.dp)
                )

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 32.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.Center,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Button(
                                onClick = { navController.navigate("subjects") },
                                modifier = Modifier
                                    .size(160.dp)
                                    .padding(16.dp),
                                shape = RoundedCornerShape(60.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = orangeColor,
                                    contentColor = whiteColor
                                )
                            ) {
                                Text("Subjects", fontSize = 16.sp, textAlign = TextAlign.Center)
                            }

                            Spacer(modifier = Modifier.width(32.dp))

                            Button(
                                onClick = { navController.navigate("study_plans") },
                                modifier = Modifier
                                    .size(160.dp)
                                    .padding(16.dp),
                                shape = RoundedCornerShape(60.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = orangeColor,
                                    contentColor = whiteColor
                                )
                            ) {
                                Text("Study Plans", fontSize = 16.sp, textAlign = TextAlign.Center)
                            }
                        }

                        Spacer(modifier = Modifier.height(32.dp))

                        Row(
                            horizontalArrangement = Arrangement.Center,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Button(
                                onClick = { navController.navigate("study_progress") },
                                modifier = Modifier
                                    .size(160.dp)
                                    .padding(16.dp),
                                shape = RoundedCornerShape(60.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = orangeColor,
                                    contentColor = whiteColor
                                )
                            ) {
                                Text("Progress", fontSize = 16.sp, textAlign = TextAlign.Center)
                            }

                            Spacer(modifier = Modifier.width(32.dp))

                            Button(
                                onClick = { navController.navigate("offline_mode") },
                                modifier = Modifier
                                    .size(160.dp)
                                    .padding(16.dp),
                                shape = RoundedCornerShape(60.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = orangeColor,
                                    contentColor = whiteColor
                                )
                            ) {
                                Text("Offline", fontSize = 16.sp, textAlign = TextAlign.Center)
                            }
                        }
                    }
                }
            }
        }
    }
}

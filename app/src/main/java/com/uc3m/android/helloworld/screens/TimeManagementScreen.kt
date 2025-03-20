package com.uc3m.android.helloworld.screens

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun TimeManagementScreen(navController: NavController) {
    val titleColor = Color(0xFFFF9966) // Naranja claro
    // List of time management tips
    val timeManagementTips = listOf(
        "⏰ Prioritize tasks: Identify the most important tasks and tackle them first.",
        "🗂 Break tasks into smaller chunks: Break large tasks into smaller, manageable tasks to avoid feeling overwhelmed.",
        "📝 Create a to-do list: Write down tasks to stay organized and keep track of your progress.",
        "📅 Plan ahead: Use a calendar or planner to schedule tasks and manage your time effectively.",
        "📌 Set deadlines: Assign deadlines to tasks, even if they are self-imposed, to create urgency.",
        "📍 Avoid multitasking: Focus on one task at a time to ensure better quality and faster completion.",
        "🔔 Use time blocks: Set specific time blocks for different activities and stick to them.",
        "💡 Take regular breaks: Short breaks help maintain focus and prevent burnout.",
        "🚫 Learn to say no: Avoid overcommitting yourself and focus on tasks that align with your goals."
    )

    // Main container for the screen
    Surface(modifier = Modifier.fillMaxSize().background(Color(0xFFFFFFFF)).padding(16.dp), color = Color(0xFFFFFFFF)) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(40.dp))
            // Title of the screen
            Text(
                text = "Time Management Tips",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = titleColor,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.height(20.dp))

            // Title of the time management tips list
            Text(
                text = "Useful Tips",
                fontSize = 24.sp,
                color = titleColor,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.height(20.dp))

            // List of time management tips
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(vertical = 16.dp)
            ) {
                items(timeManagementTips) { tip ->
                    TimeManagementTipItem(tip)
                    Spacer(modifier = Modifier.height(12.dp)) // Space between tips
                }
            }

            // Button to go back to the main screen
            Spacer(modifier = Modifier.height(20.dp))
            Button(
                onClick = { navController.popBackStack() },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text("Back to Main Screen")
            }
        }
    }
}

@Composable
fun TimeManagementTipItem(tip: String) {
    // Container for each time management tip
    Card(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFFF9966)),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = tip,
                fontSize = 16.sp,
                color = Color.White
            )
        }
    }
}

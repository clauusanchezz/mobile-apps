package com.uc3m.android.helloworld.screens

import android.content.Context
import android.widget.Toast
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
import androidx.navigation.compose.rememberNavController

@Composable
fun PrepExams(navController: NavController) {
    val titleColor = Color(0xFFFF9966) // Naranja claro
    // List of tips with emojis
    val tips = listOf(
        "📅 Plan your time: Organize a study calendar",
        "📝 Take good notes: Keep your notes organized and clear",
        "☕ Take proper breaks: Don’t forget to take breaks during study sessions",
        "💡 Create summaries: Summarize important information in small summaries or mind maps",
        "📖 Study actively: Practice exercises and ask yourself questions about the topics",
        "🎧 Listen to soft music: If it helps you focus, listen to instrumental music",
        "📚 Review regularly: Periodically review the topics to keep the knowledge fresh.",
        "🤔 Do mock exams: Practice with past exams to familiarize yourself with it",
        "🥤 Stay hydrated and eat well: Drink water and eat healthy foods to maintain energy",
        "🛌 Get enough sleep: Make sure you get enough rest before the exam."
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
                text = "Preparation of Exams",
                fontSize = 32.sp,
                fontWeight = FontWeight.ExtraBold,
                color = titleColor
            )

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Useful Tips",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = titleColor
            )

            Spacer(modifier = Modifier.height(20.dp))

            // List of tips
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(vertical = 16.dp)
            ) {
                items(tips) { tip ->
                    TipItem(tip)
                    Spacer(modifier = Modifier.height(12.dp)) // Space between tips
                }
            }

            // Button to go back to the study plans screen
            Spacer(modifier = Modifier.height(20.dp))
            Button(
                onClick = { navController.popBackStack() },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text("Back to Study Plans")
            }
        }
    }
}

@Composable
fun TipItem(tip: String) {
    // Container for each tip
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



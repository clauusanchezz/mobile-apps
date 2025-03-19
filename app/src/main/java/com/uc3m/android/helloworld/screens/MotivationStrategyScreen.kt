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
fun MotivationStrategyScreen(navController: NavController) {
    // List of motivation strategies
    val motivationTips = listOf(
        "🔥 Stay positive: Keep a positive mindset about your studies and goals.",
        "🎯 Set clear goals: Define short-term and long-term goals to keep your focus.",
        "📅 Create a study schedule: Organize your time efficiently to avoid procrastination.",
        "🏆 Reward yourself: Celebrate small victories to keep your spirits high.",
        "🧘 Stay balanced: Don’t forget to balance study time with relaxation and hobbies.",
        "💪 Stay consistent: The key to success is consistency in your efforts.",
        "📚 Surround yourself with inspiration: Keep motivational quotes or reminders visible.",
        "🚶‍♀️ Take care of your health: Exercise and eat well to maintain energy and focus.",
        "🎧 Listen to motivating music: Find songs that boost your energy and focus.",
        "🌟 Believe in yourself: Trust your abilities and keep pushing forward, even in tough times."
    )

    // Main container for the screen
    Surface(modifier = Modifier.fillMaxSize().background(Color(0xFFFF9966)).padding(16.dp), color = Color(0xFFFF9966)) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Title of the screen
            Text(
                text = "Motivation Strategies",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.height(20.dp))

            // Title of the motivation strategies list
            Text(
                text = "Useful Tips",
                fontSize = 24.sp,
                color = Color.Black,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.height(20.dp))

            // List of motivation tips
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(vertical = 16.dp)
            ) {
                items(motivationTips) { tip ->
                    MotivationTipItem(tip)
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
fun MotivationTipItem(tip: String) {
    // Container for each motivation tip
    Card(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFfc4b08)),
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

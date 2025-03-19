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
fun WorkingInTeamsScreen(navController: NavController) {
    // List of tips for working in teams
    val teamworkTips = listOf(
        "🤝 Communicate openly: Effective communication is key to a successful team.",
        "🎯 Define clear roles: Everyone should know their responsibilities to avoid confusion.",
        "🧑‍🤝‍🧑 Collaborate and share ideas: Encourage everyone to contribute to discussions and solutions.",
        "⏰ Manage time effectively: Set deadlines for tasks and ensure everyone sticks to them.",
        "💬 Listen to others: Respect the opinions and ideas of other team members.",
        "🌟 Stay positive: Keep a positive attitude to inspire and motivate others in the team.",
        "🔄 Be flexible: Adapt to new ideas and ways of working that may emerge in the team.",
        "🎉 Celebrate success: Acknowledge and celebrate milestones and achievements to boost morale.",
        "🔍 Provide constructive feedback: Offer helpful and respectful feedback to help each other improve.",
    )

    // Main container for the screen
    Surface(modifier = Modifier.fillMaxSize().background(Color(0xFFFF9966)).padding(16.dp), color = Color(0xFFFF9966)) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Title of the screen
            Text(
                text = "Teamwork Tips",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.height(20.dp))

            // Title of the teamwork tips list
            Text(
                text = "Useful Tips",
                fontSize = 24.sp,
                color = Color.Black,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.height(20.dp))

            // List of teamwork tips
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(vertical = 16.dp)
            ) {
                items(teamworkTips) { tip ->
                    TeamworkTipItem(tip)
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
fun TeamworkTipItem(tip: String) {
    // Container for each teamwork tip
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

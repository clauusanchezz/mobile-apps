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
fun ReviewClarificationScreen(navController: NavController) {
    // List of tips for reviewing and clarifying doubts
    val tips3 = listOf(
        "🔄 Revise your notes regularly: Go over your class notes periodically to reinforce your understanding",
        "❓ Ask questions: Don’t hesitate to ask about concepts you don't understand",
        "📚 Summarize key points: Make summaries or flashcards to clarify the most important topics",
        "🧑‍🏫 Attend review sessions: Participate in review or Q&A sessions",
        "🔍 Revisit challenging topics: Focus on the areas you find most difficult",
        "💬 Engage in group study: Discuss difficult topics with classmates",
        "🎯 Clarify doubts immediately: If something is unclear in class, seek clarification at the moment, don’t wait",
        "📖 Read additional materials: Supplement your class materials with additional readings",
        "💡 Teach others: Explaining what you’ve learned to someone else helps you understanding",
        "🧘 Stay calm and patient: Give yourself time to process and understand complex information"
    )

    // Main container for the screen
    Surface(modifier = Modifier.fillMaxSize().background(Color(0xFFFF9966)).padding(16.dp), color = Color(0xFFFF9966)) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Title of the screen
            Text(
                text = "Review & Clarifications",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.height(20.dp))

            // Title of the tips list
            Text(
                text = "Useful Tips",
                fontSize = 24.sp,
                color = Color.Black,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.height(20.dp))

            // List of tips
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(vertical = 16.dp)
            ) {
                items(tips3) { tip3 ->
                    TipItem3(tip3)
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
fun TipItem3(tip3: String) {
    // Container for each tip
    Card(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFfc4b08)),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = tip3,
                fontSize = 16.sp,
                color = Color.White
            )
        }
    }
}

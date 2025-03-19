package com.uc3m.android.helloworld

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.uc3m.android.helloworld.ui.theme.HelloWorldTheme
import com.uc3m.android.helloworld.screens.LoginScreen
import com.uc3m.android.helloworld.screens.SignUpScreen
import com.uc3m.android.helloworld.screens.HomeScreen
import com.uc3m.android.helloworld.screens.SubjectScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HelloWorldTheme {
                val navController = rememberNavController()
                NavHost(navController, startDestination = "login") {
                    composable("login") { LoginScreen(navController) }
                    composable("home") { HomeScreen() }
                    composable("signup") { SignUpScreen(navController) }
                    composable("subjects") { SubjectScreen(navController) }

                }
            }
        }
    }
}

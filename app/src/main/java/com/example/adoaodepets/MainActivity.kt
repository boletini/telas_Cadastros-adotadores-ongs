package com.example.adoaodepets

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.adoaodepets.screens.DetailsScreens
import com.example.adoaodepets.screens.HomeScreen
import com.example.adoaodepets.screens.TelaCriarConta
import com.example.adoaodepets.ui.theme.AdoçaoDePetsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AdoçaoDePetsTheme {
                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = "home"
                ) {
                    composable("home") {
                        HomeScreen(navController)
                    }
                    composable("details") {
                        DetailsScreens(navController)
                    }
                    composable("create_account") {
                        TelaCriarConta(navController)
                    }
                }
            }
        }
    }
}

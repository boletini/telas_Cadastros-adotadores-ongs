package com.example.adoaodepets

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.adoaodepets.screens.HomeCadastro
import com.example.adoaodepets.screens.TelaCriarConta
import com.example.adoaodepets.screens.TelaInicial
import com.example.adoaodepets.screens.TelaInicialCadastrar

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var navController = rememberNavController()
            NavHost(
                navController = navController,
                startDestination = "home"
            ){
                composable(route = "home") {
                    TelaInicial(navController)
                }
                composable(route = "home_cadastro") {
                    HomeCadastro(navController)
                }
                composable(route = "user_data") {
                    TelaInicialCadastrar(navController)
                }
                composable(route = "result_screen") {
                    TelaCriarConta(navController)
                }
            }
        }

    }
}


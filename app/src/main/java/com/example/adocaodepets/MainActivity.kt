package br.senai.sp.jandira.com.example.adocaodepets

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.adocaodepets.screens.ListaPetsScreen
import com.example.adocaodepets.screens.TelaCadastrarAnimais
import com.example.adocaodepets.screens.TelaCriarConta
import com.example.adocaodepets.screens.TelaInicial
import com.example.adocaodepets.screens.TelaInicialCadastrar
import com.example.adocaodepets.screens.TelaLogin
import com.example.adocaodepets.ui.theme.AdoçaoDePetsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AdoçaoDePetsTheme {
                TelaNavigation()
            }
        }
    }
}

@Composable
fun TelaNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "tela_inicial"
    ) {
        composable("tela_inicial") {
            TelaLogin(navController)
        }

        composable("tela_inicial_cadastrar_animal") {
            TelaCadastrarAnimais(navController)
        }

//        composable("tela_inicial") {
//            TelaInicial(navController)
//        }
        composable("tela_login") {
            TelaLogin(navController)
        }
        composable("tela_inicial_cadastrar") {
            TelaInicialCadastrar(navController)
        }
        composable("tela_cadastro") {
            TelaCriarConta(navController)
        }

        composable("tela-animais-cadastrados") {
            ListaPetsScreen(navController)
        }
    }
}

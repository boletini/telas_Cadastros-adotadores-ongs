package com.example.adoaodepets.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun HomeScreen(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF8D6E63))
    ) {
        Image(
            painter = painterResource(com.example.adoaodepets.R.drawable.cachorro_caixa),
            contentDescription = "Imagem menino na caixa",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(345.dp)
                .align(Alignment.Center)
                .offset(y = (-200).dp)
        )


        Button(
            onClick = {navController.navigate("user_data")},
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 200.dp)
                .size(width = 180.dp, height = 60.dp),


            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF4E342E),
                contentColor = Color.White

            )
        ) {

            Text(
                text = "Entrar",
                fontSize = 30.sp
            )


        }
    }
}


@Preview(showBackground = true)
@Composable
fun TelaHomePreview() {
    val navController = rememberNavController() // mock navController só para preview
    HomeScreen(navController)
}
package com.example.adocaodepets.screens

import androidx.compose.foundation.Image
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.senai.sp.jandira.com.example.adocaodepets.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RedefinirSenhaScreen(navController: NavController?) {

    var email by remember { mutableStateOf(TextFieldValue("")) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF8F8DC)) // fundo bege claro
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            // Ícone de pata
            Image(
                painter = painterResource(id = R.drawable.pata), // Substitua pelo ID do ícone de pata
                contentDescription = "Pata",
                modifier = Modifier
                    .size(40.dp)
                    .align(Alignment.End)
            )

            Spacer(modifier = Modifier.height(80.dp))

            Text(
                text = "Redefinir Senha",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Informe o email para o qual deseja redefinir a senha",
                fontSize = 16.sp,
                color = Color.Black,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Campo de Email com fundo marrom e ícone
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF4E342E), shape = RoundedCornerShape(4.dp))
                    .padding(horizontal = 8.dp, vertical = 4.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Email,
                        contentDescription = "Email Icon",
                        tint = Color.White
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    TextField(
                        value = email,
                        onValueChange = { email = it },
                        placeholder = { Text("Email:") },
                        colors = TextFieldDefaults.textFieldColors(
                            containerColor = Color.Transparent
                        ),
                        modifier = Modifier.fillMaxWidth()
                    )

                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Botão "Redefinir Senha"
            Button(
                onClick = { /* Ação de redefinir senha */ },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4E342E)),
                shape = RoundedCornerShape(50),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text(
                    text = "Redefinir Senha",
                    color = Color.White,
                    fontSize = 16.sp
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Texto "Voltar ao Login"
            Text(
                text = "Voltar ao Login",
                fontSize = 14.sp,
                color = Color(0xFF4E342E),
                modifier = Modifier
                    .clickable {
                        navController?.navigate("TelaLogin")
                    }
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun RedefinirSenhaScreenPreview() {
    RedefinirSenhaScreen(null)
}

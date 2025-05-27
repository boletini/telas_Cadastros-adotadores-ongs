package com.example.adoaodepets.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.navigation.NavController
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.adoaodepets.R

@Composable


fun HomeCadastro(navController: NavController?  = null) {
    Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF5F5DC)) // fundo beg
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(R.string.entrar),
                modifier = Modifier
                    .padding(10.dp),
                fontSize = 40.sp,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(9.dp))

            Image(
                painter = painterResource(id = R.drawable.cachorro_caixa),
                contentDescription = "",
                modifier = Modifier.height(300.dp)
                    .width(300.dp)
            )

            Spacer(modifier = Modifier.height(30.dp))

            OutlinedTextField(
                value = "",
                onValueChange = {},
                label = { Text(stringResource(R.string.email_loginCuidador)) },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = "",
                onValueChange = {},
                label = { Text(stringResource(R.string.senha_login_cuidador)) },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(50.dp))

            Text(
                text = stringResource(R.string.esqueceuSenha_login_cuidador),
                fontSize = 12.sp,
                color = Color.Black,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 8.dp),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    if (navController != null) {
                        navController.navigate("user_data")
                    }
                },
                shape = RoundedCornerShape(50),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF4E342E) // marrom
                ),
                modifier = Modifier
                    .width(150.dp)
                    .height(45.dp)
                    .align(Alignment.CenterHorizontally)
            ) {
                Text(
                    text = stringResource(R.string.entrarBotao_login_cuidador),
                    color = Color.White // texto branco para contraste
                )
            }

        }
    }
}







@Preview(showSystemUi = true)
@Composable
private fun HomeCadastroPreview() {
    HomeCadastro(null)
}
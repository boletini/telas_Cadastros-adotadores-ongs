package com.example.adocaodepets.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.senai.sp.jandira.com.example.adocaodepets.R

@Composable
fun TelaInicialCadastrar(navController: NavController?) {
    
    

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF6D4C41)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        // Saudação com ícone
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(R.string.MENSAGEM_usuario),
                color = Color.White,
                fontSize = 60.sp
            )
            Spacer(modifier = Modifier.width(15.dp))
            Image(
                painter = painterResource(id = R.drawable.pata),
                contentDescription = stringResource(R.string.icone_pata),
                modifier = Modifier.size(70.dp)
            )
        }

        // Mensagem de boas-vindas
        Text(
            text = stringResource(R.string.mensagem_boas_vindas),
            color = Color.White,
            fontSize = 25.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 24.dp)
        )

        // Convite para cadastro
        Text(
            text = stringResource(R.string.mensagem_convite),
            color = Color.White,
            fontSize = 25.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 24.dp)
        )

        Spacer(modifier = Modifier.width(7.dp))

        // Botão “Cadastrar”
        Button(
            onClick = { navController?.navigate("tela_cadastro") },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF4E342E)   // marrom
            ),
            shape = RoundedCornerShape(50),
            elevation = ButtonDefaults.buttonElevation(defaultElevation = 0.dp),
            modifier = Modifier
                .width(180.dp)
                .height(50.dp)
        ) {
            Text(
                text = stringResource(R.string.botao_cadastrar),
                fontSize = 20.sp
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun TelaInicialCadastrarPreview() {
    TelaInicialCadastrar(navController = null)
}

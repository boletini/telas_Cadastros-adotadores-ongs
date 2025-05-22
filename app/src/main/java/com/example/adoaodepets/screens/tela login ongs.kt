package com.example.adoaodepets.screens


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.adoaodepets.R

@Composable
fun TelaCriarConta() {


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5DC)), // fundo bege claro
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {

        // Título com ícone
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(R.string.titulo_criar_conta),
                fontSize = 50.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Spacer(modifier = Modifier.width(8.dp))
            Image(
                painter = painterResource(id = R.drawable.pata), // ícone de pata
                contentDescription = stringResource(R.string.icone_pata),
                modifier = Modifier.size(60.dp)
            )
        }

        // Campos de entrada
        Column(modifier = Modifier.padding(horizontal = 32.dp)) {
            OutlinedTextField(
                value = "nome",
                onValueChange = {},
                label = { Text(text = stringResource(R.string.label_nome)) },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = "email",
                onValueChange = {},
                label = { Text(text = stringResource(R.string.label_email)) },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = "telefone",
                onValueChange = {},
                label = { Text(text = stringResource(R.string.label_telefone)) },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Dropdown customizado
            OutlinedTextField(
                value = "",
                onValueChange = {},
                readOnly = true,
                trailingIcon = {
                    Icon(Icons.Filled.ArrowDropDown, contentDescription = null)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { /* Abrir menu se necessário */ }
            )
        }

        // Texto: Faz parte de uma ONG?
        Text(
            text = stringResource(R.string.pergunta_ong),
            fontSize = 14.sp,
            color = Color.Black,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 16.dp)
        )

        // Botão
        Button(
            onClick = { /* ação de cadastro */ },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFE0DED4)
            ),
            shape = RoundedCornerShape(50),
            elevation = ButtonDefaults.buttonElevation(defaultElevation = 0.dp),
            modifier = Modifier
                .width(160.dp)
                .height(40.dp)
        ) {
            Text(
                text = stringResource(R.string.botao_cadastrar),
                color = Color(0xFF4E342E),
                fontSize = 14.sp
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun TelaCadastroOngsPreview() {
    TelaCriarConta()
}

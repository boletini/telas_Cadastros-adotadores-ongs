package com.example.adocaodepets.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.com.example.adocaodepets.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TelaCadastrarAnimais() {

    var nome by remember { mutableStateOf(TextFieldValue("")) }
    var descricao by remember { mutableStateOf(TextFieldValue("")) }
    var idade by remember { mutableStateOf(TextFieldValue("")) }
    var sexo by remember { mutableStateOf(TextFieldValue("")) }
    var raca by remember { mutableStateOf(TextFieldValue("")) }
    var contato by remember { mutableStateOf(TextFieldValue("")) }
    var status by remember { mutableStateOf(TextFieldValue("")) }
    var temperamento by remember { mutableStateOf(TextFieldValue("")) }
    var especie by remember { mutableStateOf(TextFieldValue("")) }
    var vacinas by remember { mutableStateOf(TextFieldValue("")) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFF8E1))
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            /** TOPO **/
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(R.drawable.logo),
                    contentDescription = "Logo",
                    modifier = Modifier.size(60.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "LAR DE PATAS",
                    fontSize = 20.sp,
                    color = Color(0xFF4E342E),
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.width(8.dp))
                Divider(
                    color = Color(0xFF4E342E),
                    modifier = Modifier
                        .height(24.dp)
                        .width(1.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Pesquise um pet cadastrado",
                    fontSize = 14.sp,
                    color = Color(0xFF4E342E)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Buscar",
                    tint = Color(0xFF4E342E)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            /** FORMULÁRIO **/
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color.White)
                    .padding(16.dp)
            ) {
                /** IMAGEM DE UPLOAD **/
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color(0xFFFFF8E1)),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Image(
                            painter = painterResource(id = R.drawable.cachorro_caixa), // coloque o ícone correto
                            contentDescription = "Upload",
                            modifier = Modifier.size(50.dp)
                        )
                        Text(
                            text = "Carregar\nfoto do pet",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF4E342E),
                            textAlign = TextAlign.Center
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                /** CAMPOS **/
                CampoTexto(label = "NOME:", valor = nome) { nome = it }
                CampoTexto(label = "DESCRIÇÃO:", valor = descricao, altura = 100.dp) { descricao = it }

                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    CampoTexto(label = "IDADE:", valor = idade, largura = 100.dp) { idade = it }
                    CampoTexto(label = "SEXO:", valor = sexo, largura = 100.dp) { sexo = it }
                }

                CampoTexto(label = "RAÇA:", valor = raca) { raca = it }
                CampoTexto(label = "CONTATO DO DONO:", valor = contato) { contato = it }

                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    CampoTexto(label = "STATUS:", valor = status, largura = 150.dp) { status = it }
                    CampoTexto(label = "TEMPERAMENTO:", valor = temperamento, largura = 150.dp) { temperamento = it }
                }

                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    CampoTexto(label = "ESPÉCIE:", valor = especie, largura = 150.dp) { especie = it }
                    CampoTexto(label = "VACINAS:", valor = vacinas, largura = 150.dp) { vacinas = it }
                }

                Spacer(modifier = Modifier.height(16.dp))

                /** BOTÃO **/
                Button(
                    onClick = { /* ação de cadastro */ },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF4E342E),
                        contentColor = Color.White
                    ),
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(50)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.pata), // coloque o ícone de pata
                        contentDescription = "Pata",
                        tint = Color.White,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = "CADASTRAR")
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CampoTexto(
    label: String,
    valor: TextFieldValue,
    altura: Dp = 56.dp,
    largura: Dp = Dp.Unspecified,
    aoMudar: (TextFieldValue) -> Unit
) {
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun CampoTexto(
        label: String,
        valor: TextFieldValue,
        altura: Dp = 56.dp,
        largura: Dp = Dp.Unspecified,
        aoMudar: (TextFieldValue) -> Unit
    ) {
        Column(
            modifier = Modifier
                .padding(vertical = 4.dp)
                .then(
                    if (largura != Dp.Unspecified) Modifier.width(largura)
                    else Modifier.fillMaxWidth()
                )
        ) {
            Text(
                text = label,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF4E342E),
                fontSize = 12.sp
            )
            OutlinedTextField(
                value = valor,
                onValueChange = aoMudar,
                modifier = Modifier
                    .height(altura)
                    .fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color(0xFF4E342E),
                    unfocusedBorderColor = Color(0xFF4E342E)
                )
            )
        }
    }

}

@Preview(showSystemUi = true)
@Composable
private fun TelaCadastrarAnimaisPreview() {
    TelaCadastrarAnimais()
}

//package com.example.adocaodepets.screens
//
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.background
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.shape.CircleShape
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.foundation.text.BasicTextField
//import androidx.compose.material3.*
//import androidx.compose.runtime.*
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.layout.ContentScale
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.text.input.TextFieldValue
//import androidx.compose.ui.text.style.TextAlign
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import androidx.navigation.NavController
//import br.senai.sp.jandira.com.example.adocaodepets.R
//import coil.compose.rememberAsyncImagePainter
//
//@Composable
//fun TelaPets(navController: NavController?) {
//    var searchText by remember { mutableStateOf(TextFieldValue("")) }
//
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(Color(0xFFFAF6DC)) // Fundo bege claro
//            .padding(16.dp)
//    ) {
//        // Cabeçalho com ícone
//        Row(
//            modifier = Modifier.fillMaxWidth(),
//            verticalAlignment = Alignment.CenterVertically
//        ) {
//            Icon(
//                painter = painterResource(id = R.drawable.pata), // coloque o ícone da pata no drawable
//                contentDescription = "Pata",
//                tint = Color(0xFF4E342E),
//                modifier = Modifier.size(32.dp)
//            )
//            Spacer(modifier = Modifier.width(8.dp))
//            Text(
//                text = "Lar de Pets",
//                fontWeight = FontWeight.Bold,
//                fontSize = 24.sp,
//                color = Color(0xFF4E342E)
//            )
//        }
//
//        Spacer(modifier = Modifier.height(16.dp))
//
//        // Campo de busca
//        Row(
//            verticalAlignment = Alignment.CenterVertically,
//            modifier = Modifier
//                .fillMaxWidth()
//                .background(Color.Transparent)
//                .padding(horizontal = 8.dp)
//        ) {
//            BasicTextField(
//                value = searchText,
//                onValueChange = { searchText = it },
//                singleLine = true,
//                modifier = Modifier
//                    .weight(1f)
//                    .background(Color.Transparent),
//                decorationBox = { innerTextField ->
//                    if (searchText.text.isEmpty()) {
//                        Text(
//                            text = "Pesquise um pet cadastrado",
//                            color = Color(0xFF4E342E),
//                            fontSize = 16.sp
//                        )
//                    }
//                    innerTextField()
//                }
//            )
//            Spacer(modifier = Modifier.width(8.dp))
//            Icon(
//                painter = painterResource(id = R.drawable.pata), // ícone de busca no drawable
//                contentDescription = "Buscar",
//                tint = Color(0xFF4E342E),
//                modifier = Modifier.size(24.dp)
//            )
//        }
//
//        Divider(
//            color = Color(0xFF4E342E),
//            thickness = 4.dp,
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(vertical = 12.dp)
//        )
//
//        // Título "Pets Cadastrados"
//        Text(
//            text = "PETS CADASTRADOS",
//            fontWeight = FontWeight.Bold,
//            fontSize = 20.sp,
//            color = Color(0xFF4E342E),
//            modifier = Modifier.fillMaxWidth(),
//            textAlign = TextAlign.Center
//        )
//
//        Spacer(modifier = Modifier.height(16.dp))
//
//        // Grid de cards
//        Column(
//            modifier = Modifier.fillMaxWidth()
//        ) {
//            repeat(3) { // 3 linhas
//                Row(
//                    modifier = Modifier.fillMaxWidth(),
//                    horizontalArrangement = Arrangement.SpaceBetween
//                ) {
//                    repeat(2) { // 2 colunas
//                        PetCard()
//                    }
//                }
//                Spacer(modifier = Modifier.height(16.dp))
//            }
//        }
//    }
//}
//
//@Composable
//fun PetCard() {
//    Column(
//        modifier = Modifier
//            .width(140.dp)
//            .background(Color.White, shape = RoundedCornerShape(16.dp))
//            .padding(8.dp)
//    ) {
//        Image(
//            image = painterResource(), // Exemplo de imagem de pug
//            contentDescription = "Imagem do Pet",
//            contentScale = ContentScale.Crop,
//            modifier = Modifier
//                .height(120.dp)
//                .fillMaxWidth()
//                .background(Color.LightGray, shape = RoundedCornerShape(12.dp))
//        )
//        Spacer(modifier = Modifier.height(8.dp))
//        Button(
//            onClick = { /* Ação */ },
//            shape = RoundedCornerShape(50),
//            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4E342E)),
//            modifier = Modifier.fillMaxWidth()
//        ) {
//            Text(
//                text = "MAIS INFORMAÇÕES",
//                color = Color.White,
//                fontSize = 12.sp
//            )
//        }
//    }
//}
//
//@Preview(showSystemUi = true)
//@Composable
//fun TelaPetsPreview() {
//    TelaPets(null)
//}

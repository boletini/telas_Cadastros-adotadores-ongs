package com.example.adocaodepets.screens.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.com.example.adocaodepets.R
import coil.compose.AsyncImage
import androidx.compose.ui.layout.ContentScale

@Composable
fun cardAnimal(
    imageAnimal: String,
    isFilled: Boolean = false
){
    Box(
        modifier = Modifier
            .height(188.dp)
            .width(140.dp),
        contentAlignment = Alignment.Center
    ){
        Card(
            modifier = Modifier.fillMaxSize(),
            shape = RoundedCornerShape(12.dp),
            border = BorderStroke(5.dp, Color(0xFF4E342E))
        ) {
            AsyncImage(
                model = if (imageAnimal.isNotEmpty()) imageAnimal else R.drawable.image_not_found,
                contentDescription = "Imagem do animal",
                contentScale = ContentScale.FillBounds
            )
        }

        IconButton(
            onClick = {},
            modifier = Modifier
                .width(115.dp)
                .height(40.dp)
                .align(Alignment.BottomCenter)
                .padding(bottom = 12.dp),
            colors = IconButtonDefaults.iconButtonColors(
                containerColor = Color(0xFF4E342E)
            )
        ) {
            Text(
                text = "MAIS INFORMAÇÕES",
                color = Color.White,
                textAlign = TextAlign.Center,
                fontSize = 10.sp,
                fontFamily = FontFamily.SansSerif
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun cardAnimalPreview(){
    cardAnimal(
        imageAnimal = "",
    )
}


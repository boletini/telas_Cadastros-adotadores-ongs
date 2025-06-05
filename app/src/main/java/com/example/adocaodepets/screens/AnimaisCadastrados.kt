package com.example.adocaodepets.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.senai.sp.jandira.com.example.adocaodepets.R
import com.example.adocaodepets.model.homeAnimal
import com.example.adocaodepets.model.resultListaAnimal
import com.example.adocaodepets.screens.components.cardAnimal
import com.example.adocaodepets.service.RetrofitFactory
import okhttp3.Call
import okhttp3.Callback
import retrofit2.Response

data class Pet(val id: Int, val nome: String, val imagemResId: Int)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListaPetsScreen(navController: NavController?) {

    var busca by remember { mutableStateOf(TextFieldValue("")) }

    var animalList by remember {
        mutableStateOf(listOf<homeAnimal>())
    }

    val call = RetrofitFactory().getListaDeAnimais().listarAnimais()

    call.enqueue(object : retrofit2.Callback<resultListaAnimal>{
        override fun onResponse(call: retrofit2.Call<resultListaAnimal>, response: Response<resultListaAnimal>) {
            val responseBody = response.body()

            if (responseBody != null) {
                val animais = responseBody.animais
                if (animais != null) {
                    animalList = animais
                } else {
                    Log.e("API", "Results veio nulo")
                }
            } else {
                Log.e("API", "Body veio nulo")
            }


        }

        override fun onFailure(p0: retrofit2.Call<resultListaAnimal?>, p1: Throwable) {
            Log.e("API", "Falha na requisição: ${p1.message}")
        }
    })


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF8F8DC))
            .padding(15.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(bottom = 16.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.logoanimal),
                contentDescription = "Logo",
                modifier = Modifier
                    .size(80.dp)
                    .height(30.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Lar de Patas",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF4E342E)
            )
        }

        Divider(
            color = Color(0xFF4E342E),
            thickness = 5.dp,
            modifier = Modifier.padding(vertical = 8.dp)
        )

        // Campo de busca
        OutlinedTextField(
            value = busca,
            onValueChange = { busca = it },
            placeholder = { Text("Pesquise um pet cadastrado") },
            singleLine = true,
            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Buscar",
                    tint = Color(0xFF4E342E)
                )
            },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color(0xFF4E342E),
                unfocusedBorderColor = Color(0xFF4E342E),
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        )

        Divider(
            color = Color(0xFF4E342E),
            thickness = 5.dp,
            modifier = Modifier.padding(vertical = 8.dp)
        )

        Text(
            text = "PETS CADASTRADOS",
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF4E342E),
            modifier = Modifier.padding(bottom = 8.dp)
        )

        // Grid de pets
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(animalList) {
                cardAnimal(
                    imageAnimal = it.foto,
                )
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun listaPetsScreenPreview() {
    ListaPetsScreen(navController = null)
}
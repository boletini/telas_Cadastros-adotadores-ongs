package com.example.adocaodepets.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.senai.sp.jandira.com.example.adocaodepets.R
import com.example.adocaodepets.model.Cadastro_Usuario
import com.example.adocaodepets.service.RetrofitFactory
import android.widget.Toast
import androidx.compose.ui.platform.LocalContext


@Composable
fun TelaCriarConta(navController: NavController?) {

    var nome = remember { mutableStateOf("") }
    var email = remember { mutableStateOf("") }
    var telefone = remember { mutableStateOf("") }

    val context = LocalContext.current


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5DC)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {

        // Título com ícone
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = stringResource(R.string.titulo_criar_conta),
                fontSize = 50.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Spacer(modifier = Modifier.width(8.dp))
            Image(
                painter = painterResource(id = R.drawable.pata),
                contentDescription = stringResource(R.string.icone_pata),
                modifier = Modifier.size(60.dp)
            )
        }

        Column(modifier = Modifier.padding(horizontal = 32.dp)) {
            OutlinedTextField(
                value = nome.value,
                onValueChange = { it ->
                    nome.value = it
                },
                label = { Text(stringResource(R.string.label_nome), fontSize = 18.sp) },
                leadingIcon = {
                    Image(
                        painter = painterResource(R.drawable.nome),
                        contentDescription = ""
                    )
                },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = email.value,
                onValueChange = { it ->
                    email.value = it
                },
                label = { Text(stringResource(R.string.label_email), fontSize = 18.sp) },
                leadingIcon = {
                    Image(
                        painter = painterResource(R.drawable.email),
                        contentDescription = ""
                    )
                },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = telefone.value,
                onValueChange = { it ->
                    telefone.value = it
                },
                label = {
                    Text(stringResource(R.string.label_telefone), fontSize = 18.sp) },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Phone,
                        contentDescription = null,
                        tint = Color.Black
                    )
                },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = "", // você pode implementar uma variável para dropdown
                onValueChange = {},
                label = { Text(stringResource(R.string.Voce_é)) },
                readOnly = true,
                trailingIcon = {
                    Icon(Icons.Filled.ArrowDropDown, contentDescription = null)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { /* abrir menu */ }
            )
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(bottom = 100.dp)
        ) {
            Row {
                Text(
                    text = " Já possui conta?",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Text(
                    text = " Entre",
                    color = Color(0xFF4E342E),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .clickable {
                            navController?.navigate("tela_login")
                        }
                        .padding(horizontal = 5.dp)
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = {
                    if (nome.value.isBlank() || email.value.isBlank() || telefone.value.isBlank()) {
                        Toast.makeText(
                            context,
                            "Preencha todos os campos antes de continuar.",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        val cadastro = Cadastro_Usuario(
                            nome = nome.value,
                            email = email.value,
                            telefone = telefone.value
                        )
                        val call = RetrofitFactory().getCadastro_Usuario_Service().insert(cadastro)

                        call.enqueue(object : retrofit2.Callback<Cadastro_Usuario> {
                            override fun onResponse(
                                call: retrofit2.Call<Cadastro_Usuario>,
                                response: retrofit2.Response<Cadastro_Usuario>
                            ) {
                                if (response.isSuccessful) {
                                    Log.i("API", "Usuário cadastrado com sucesso: ${response.body()}")
                                    Toast.makeText(
                                        context,
                                        "Cadastro realizado com sucesso!",
                                        Toast.LENGTH_LONG
                                    ).show()
                                } else {
                                    Log.e("API", "Erro ao cadastrar: ${response.code()}")
                                    Toast.makeText(
                                        context,
                                        "Erro ao cadastrar. Tente novamente.",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }

                            override fun onFailure(call: retrofit2.Call<Cadastro_Usuario>, t: Throwable) {
                                Log.e("API", "Falha na requisição: ${t.message}")
                                Toast.makeText(
                                    context,
                                    "Erro de conexão. Verifique sua internet.",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        })
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF4E342E)
                ),
                shape = RoundedCornerShape(50),
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 0.dp),
                modifier = Modifier
                    .width(200.dp)
                    .height(50.dp)
            ) {
                Text(
                    text = stringResource(R.string.botao_cadastrar),
                    color = Color.White,
                    fontSize = 16.sp
                )
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun TelaCriarContaPreview() {
    TelaCriarConta(null)
}

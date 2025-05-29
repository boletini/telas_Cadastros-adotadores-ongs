package com.example.adocaodepets.screens
import androidx.compose.material3.OutlinedTextField

import androidx.compose.material3.*

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown

import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.senai.sp.jandira.com.example.adocaodepets.R
import com.example.adocaodepets.model.Categoria
import com.example.adocaodepets.model.Result
import com.example.adocaodepets.model.Usuario
import com.example.adocaodepets.screens.components.CategoriaItem
import com.example.adocaodepets.service.RetrofitFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TelaCriarConta(navController: NavController?) {

    var nome by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var senha by remember { mutableStateOf("") }
    var cnpj by remember {mutableStateOf("")}
    var cpf by remember {mutableStateOf("")}
    var id_categoria: Int = 0
    var data_nascimento by remember { mutableStateOf("") }

    val context = LocalContext.current

    var categoriesList by remember {
        mutableStateOf(listOf<Categoria>())
    }

    val call = RetrofitFactory().getListaDeCategorias().listarCategorias()

    call.enqueue(object : Callback<Result>{
        override fun onResponse(call: Call<Result>, response: Response<Result>) {
            val responseBody = response.body()

            if (responseBody != null) {
                val categorias = responseBody.categorias
                if (categorias != null) {
                    categoriesList = categorias
                } else {
                    Log.e("API", "Results veio nulo")
                }
            } else {
                Log.e("API", "Body veio nulo")
            }


        }

        override fun onFailure(call: Call<Result>, t: Throwable) {
            Log.e("API", "Falha na requisição: ${t.message}")

        }

    })


    var opcaoSelecionada by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }



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
                value = nome,
                onValueChange = { nome = it },
                label = { Text(stringResource(R.string.label_nome), fontSize = 18.sp) },
                leadingIcon = {
                    Image(
                        painter = painterResource(R.drawable.nome),
                        contentDescription = "",
                        modifier = Modifier.size(30.dp)
                    )
                },
                modifier = Modifier
                    .fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color(0xFFD9D9D9)
                ),
                shape = RoundedCornerShape(8.dp)
            )




            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text(stringResource(R.string.label_email), fontSize = 18.sp) },
                leadingIcon = {
                    Image(
                        painter = painterResource(R.drawable.email),
                        contentDescription = "",
                        modifier = Modifier.size(30.dp)
                    )
                },
                modifier = Modifier.fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color(0xFFD9D9D9)
                ),
                shape = RoundedCornerShape(8.dp)
            )

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = senha,
                onValueChange = { senha = it },
                label = { Text(stringResource(R.string.senha_login_cuidador), fontSize = 18.sp) },
                leadingIcon = {
                    Image(
                        painter = painterResource(R.drawable.senha),
                        contentDescription = "",
                        modifier = Modifier.size(30.dp)
                    )
                },
                modifier = Modifier.fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color(0xFFD9D9D9)
                ),
                shape = RoundedCornerShape(8.dp)
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Mostrar Data de Nascimento somente se não for ONG




            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = { expanded = !expanded }
            ) {
                OutlinedTextField(
                    value = opcaoSelecionada,
                    onValueChange = { },
                    readOnly = true,
                    label = { Text("Você é:") },
                    trailingIcon = {
                        Icon(
                            imageVector = Icons.Filled.ArrowDropDown,
                            contentDescription = null,
                            modifier = Modifier.size(30.dp)
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .menuAnchor(),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color(0xFFD9D9D9)
                    ),
                    shape = RoundedCornerShape(8.dp)
                )

                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    categoriesList.forEach { categoria ->
                        DropdownMenuItem(
                            text = { Text(categoria.nome_categoria) },
                            onClick = {
                                opcaoSelecionada = categoria.nome_categoria
                                id_categoria = categoria.id
                                expanded = false
                                // Limpa os campos sempre que trocar
                                cnpj = ""
                                cpf = ""
                                data_nascimento = ""
                            }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

// Mostrar campos com base na seleção
            when (opcaoSelecionada.lowercase()) {
                "tutor" -> {
                    OutlinedTextField(
                        value = data_nascimento,
                        onValueChange = { data_nascimento = it },
                        label = { Text("Data de Nascimento") },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(8.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color(0xFFD9D9D9)
                        )
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    OutlinedTextField(
                        value = cpf,
                        onValueChange = { cpf = it },
                        label = { Text("CPF") },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(8.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color(0xFFD9D9D9)
                        )
                    )

                    Spacer(modifier = Modifier.height(12.dp))
                }

                "ong" -> {
                    OutlinedTextField(
                        value = cnpj,
                        onValueChange = { cnpj = it },
                        label = { Text("CNPJ") },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(8.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color(0xFFD9D9D9)
                        )
                    )

                    Spacer(modifier = Modifier.height(12.dp))
                }
            }

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
                    // Validação simples - se for tutor, cnpj pode ser vazio; se for ong, cpf e data_nascimento podem ser vazios
                    val isValid = when (opcaoSelecionada.lowercase()) {
                        "tutor" -> nome.isNotBlank() && email.isNotBlank() && senha.isNotBlank() && data_nascimento.isNotBlank() && cpf.isNotBlank()
                        "ong" -> nome.isNotBlank() && email.isNotBlank() && senha.isNotBlank() && cnpj.isNotBlank()
                        else -> false
                    }

                    if (!isValid || opcaoSelecionada.isBlank()) {
                        Toast.makeText(
                            context,
                            "Preencha todos os campos obrigatórios antes de continuar.",
                            Toast.LENGTH_SHORT
                        ).show()
                        return@Button
                    }

                    val user = Usuario(
                        nome = nome,
                        email = email,
                        senha = senha,
                        data_nascimento = if (opcaoSelecionada.lowercase() == "tutor") data_nascimento else "",
                        cpf = if (opcaoSelecionada.lowercase() == "tutor") cpf else "",
                        cnpj = if (opcaoSelecionada.lowercase() == "ong") cnpj else "",
                        id_categoria = id_categoria
                    )

                    val call = RetrofitFactory().getCadastro_Usuario_Service().insert(user)

                    call.enqueue(object : retrofit2.Callback<Usuario> {
                        override fun onResponse(
                            call: retrofit2.Call<Usuario>,
                            response: retrofit2.Response<Usuario>
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

                        override fun onFailure(call: retrofit2.Call<Usuario>, t: Throwable) {
                            Log.e("API", "Falha na requisição: ${t.message}")
                            Toast.makeText(
                                context,
                                "Erro de conexão. Verifique sua internet.",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    })
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

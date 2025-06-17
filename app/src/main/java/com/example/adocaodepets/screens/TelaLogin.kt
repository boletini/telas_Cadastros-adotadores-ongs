package com.example.adocaodepets.screens

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.senai.sp.jandira.com.example.adocaodepets.R
import com.example.adocaodepets.model.LoginResponse
import com.example.adocaodepets.model.Usuario
import com.example.adocaodepets.model.UsuarioLogin
import com.example.adocaodepets.model.UsuarioResponse
import com.example.adocaodepets.service.RetrofitFactory
import org.junit.runner.manipulation.Ordering.Context
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TelaLogin(navController: NavController?) {

    var email by remember { mutableStateOf("") }
    var senha by remember { mutableStateOf("") }

    val context = LocalContext.current

    val sharedUserId = context.getSharedPreferences("usuarios",android.content.Context.MODE_PRIVATE)
    val editor = sharedUserId.edit()
    val userId = sharedUserId.getInt("user_id",0)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5DC)) // fundo bege
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
                modifier = Modifier.padding(10.dp),
                fontSize = 40.sp,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(9.dp))

            Image(
                painter = painterResource(id = R.drawable.cachorro_caixa),
                contentDescription = "Ilustração de cachorro em uma caixa",
                modifier = Modifier
                    .height(300.dp)
                    .width(300.dp)
            )

            Spacer(modifier = Modifier.height(30.dp))

            // Campo de Email com fundo cinza

            Column(modifier = Modifier.padding(horizontal = 12.dp)) {

                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text("Email") },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Filled.Email,
                            contentDescription = "Email Icon"
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
                    label = { Text("Senha") },
                    visualTransformation = PasswordVisualTransformation(),
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Filled.Lock,
                            contentDescription = "Lock Icon"
                        )
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color(0xFFD9D9D9)
                    ),
                    shape = RoundedCornerShape(8.dp)
                )
            }



            Spacer(modifier = Modifier.height(50.dp))

            Text(
                text = stringResource(R.string.esqueceuSenha_login_cuidador),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier
                    .clickable {
                        navController?.navigate("esqueci_senha")
                    }
                    .fillMaxWidth()
                    .padding(end = 8.dp),
                textAlign = TextAlign.Center,
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = onClick@{
                    if (email.isBlank() || senha.isBlank()) {
                        Toast.makeText(context, "Preencha todos os campos obrigatórios", Toast.LENGTH_SHORT).show()
                        return@onClick
                    }else{
                        val user = UsuarioLogin(
                            email = email,
                            senha = senha
                        )

                        val call = RetrofitFactory().InsertUsuarioLogin().insertLogin(user)

                        call.enqueue(object : Callback<LoginResponse> {
                            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                                if (response.isSuccessful) {
                                    val usuario = response.body()?.resultUsuario
                                    val id = usuario?.id

                                    // Salvando em SharedPreferences, por exemplo
                                    val editor = context.getSharedPreferences("usuarios", android.content.Context.MODE_PRIVATE).edit()
                                    editor.putInt("user_id", id ?: 0)
                                    editor.apply()
                                    Log.d("API_RESPONSE", "Body: $usuario")

                                    Toast.makeText(context, "Login realizado com sucesso!", Toast.LENGTH_LONG).show()
                                    navController?.navigate("tela_home")
                                } else {
                                    Log.e("API", "Erro: ${response.errorBody()?.string()}")
                                }
                            }

                            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                                Log.e("API", "Falha: ${t.message}")
                            }
                        })
                    }
                    navController?.navigate("tela_home")
                },
                shape = RoundedCornerShape(50),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF4E342E) // marrom
                ),
                modifier = Modifier
                    .width(170.dp)
                    .height(55.dp)
                    .align(Alignment.CenterHorizontally)
            ) {
                Text(
                    text = stringResource(R.string.entrarBotao_login_cuidador),
                    fontSize = 20.sp,
                    color = Color.White
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = stringResource(R.string.cadastre_se),
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier
                    .clickable {
                        navController?.navigate("tela_inicial_cadastrar")
                    }
                    .fillMaxWidth()
                    .padding(end = 8.dp),
                textAlign = TextAlign.Center,
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun TelaLoginPreview() {
    TelaLogin(null)
}

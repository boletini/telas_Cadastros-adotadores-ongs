package com.example.adocaodepets.screens
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.senai.sp.jandira.com.example.adocaodepets.R

@Composable
fun TelaHome(navController: NavController?) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFF8E1))
    ) {

        val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
        val scope = rememberCoroutineScope()

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(R.drawable.logo),
                    contentDescription = "",
                    modifier = Modifier
                        .size(100.dp)
                )
                Spacer(modifier = Modifier.weight(1f))

                IconButton(onClick = {}) {
                    Icon(
                        imageVector = Icons.Default.AccountCircle,
                        contentDescription = "Menu",
                        tint = Color(color = 0xFF4E342E),
                        modifier = Modifier
                            .size(80.dp)
                    )
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 35.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            )
            {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Card(
                        modifier = Modifier
                            .size(40.dp),
                        shape = RoundedCornerShape(40.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color(color = 0xFF4E342E)
                        )
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxSize(),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.home),
                                contentDescription = "",
                                modifier = Modifier
                                    .width(25.dp)
                                    .height(25.dp)
                            )
                        }
                    }

                    Text(
                        text = "Home",
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold,
                        color = Color(color = 0xFF4E342E),
                        modifier = Modifier
                            .padding(top = 5.dp)
                    )
                }
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Card(
                        modifier = Modifier
                            .size(40.dp),
                        shape = RoundedCornerShape(40.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color(color = 0xFF4E342E)
                        )
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxSize(),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.dog),
                                contentDescription = "",
                                modifier = Modifier
                                    .width(25.dp)
                                    .height(25.dp)
                            )
                        }
                    }

                    Text(
                        text = "Pedidos",
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold,
                        color = Color(color = 0xFF4E342E),
                        modifier = Modifier
                            .padding(top = 5.dp)
                    )
                }
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Card(
                        modifier = Modifier
                            .size(40.dp),
                        shape = RoundedCornerShape(40.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color(color = 0xFF4E342E)
                        )
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxSize(),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.comentarios),
                                contentDescription = "",
                                modifier = Modifier
                                    .width(25.dp)
                                    .height(25.dp)
                            )
                        }
                    }

                    Text(
                        text = "Feedback",
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold,
                        color = Color(color = 0xFF4E342E),
                        modifier = Modifier
                            .padding(top = 5.dp)
                    )
                }
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Card(
                        modifier = Modifier
                            .size(40.dp),
                        shape = RoundedCornerShape(40.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color(color = 0xFF4E342E)
                        )
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxSize(),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.form),
                                contentDescription = "",
                                modifier = Modifier
                                    .width(25.dp)
                                    .height(25.dp)
                            )
                        }
                    }

                    Text(
                        text = "Sobre nós",
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold,
                        color = Color(color = 0xFF4E342E),
                        modifier = Modifier
                            .padding(top = 5.dp)
                    )
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 35.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Um pet adotado - uma família feliz",
                    fontSize = 38.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
                Image(
                    painter = painterResource(R.drawable.pet2),
                    contentDescription = "",
                    modifier = Modifier
                        .padding(top = 20.dp)
                        .size(230.dp)
                        .padding(bottom = 15.dp)
                )
                Text(
                    text = "Na nossa ONG, acreditamos que todo animal merece um lar cheio de carinho. Resgatamos cães e gatos em situação de abandono, garantindo que recebam cuidados essenciais: vacinas, consultas veterinárias, castração e tratamento para doenças e ferimentos.\n" +
                            "\n" +
                            "Adotar transforma vidas — a do animal e a da família que o acolhe.",
                    fontSize = 14.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Normal,
                    textAlign = TextAlign.Justify
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 35.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {


                Button(
                    onClick = {
                        navController?.navigate("tela_cadastrar_animais")

                    },
                    shape = RoundedCornerShape(25.dp),
                    modifier = Modifier
                        .width(170.dp)
                        .height(50.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF4E342E)
                    )
                ) {
                    Text(
                        text = "CADASTRAR ANIMAIS",
                        color = Color.White,
                        fontSize = 13.sp,
                        textAlign = TextAlign.Center,

                        )
                }

                Button(
                    onClick = {  navController?.navigate("tela_animais_cadastrados")},
                    shape = RoundedCornerShape(25.dp),
                    modifier = Modifier
                        .width(170.dp)
                        .height(50.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF4E342E)
                    )
                ) {
                    Text(
                        text = "ANIMAIS CADASTRADOS",
                        color = Color.White,
                        fontSize = 13.sp,
                        textAlign = TextAlign.Center,
                    )
                }

            }

        }
    }
}

@Preview(showBackground = true)
@Composable
private fun TelaHomePreview() {
    TelaHome(navController = null)
}

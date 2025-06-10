package com.example.adocaodepets.screens

import android.content.Context
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.senai.sp.jandira.com.example.adocaodepets.R
import com.example.adocaodepets.model.Animal // Importar seu modelo Animal
import com.example.adocaodepets.service.RetrofitFactory
import com.example.adocaodepets.service.UploadParams
import com.example.adocaodepets.service.uploadImageToAzure
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream




@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TelaCadastrarAnimais(navController: NavController?) {

    var nome by remember { mutableStateOf("") }
    var descricao by remember { mutableStateOf("") }
    var status by remember { mutableStateOf("") } // String para o TextField
    var sexo by remember { mutableStateOf("") }
    var idade by remember { mutableStateOf("") }
    var raca by remember { mutableStateOf("") }
    var fotoUrl by remember { mutableStateOf<String?> (null)  }
    var contato_do_dono by remember { mutableStateOf("") }
    var temperamento by remember { mutableStateOf("") } // String para o TextField
    var vacina by remember { mutableStateOf("") } // String para o TextField

    var showProgressDialog by remember { mutableStateOf(false) }
    var imageUri by remember { mutableStateOf<Uri?>(null) }

    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    val galleryLauncher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        imageUri = uri
        uri?.let {
            uploadSelectedImageToAzure(context, coroutineScope, it) { uploadedUrl ->
                fotoUrl = uploadedUrl
                if (uploadedUrl != null) {
                    Log.d("TelaCadastrarAnimais", "URL da foto no Azure: $fotoUrl")
                    Toast.makeText(context, "Upload concluído!", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context, "Falha no upload.", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFF8E1))
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

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
                    fontSize = 10.sp,
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
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Button(
                    onClick = {
                        galleryLauncher.launch("image/*") // Inicia a seleção de imagem
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(color = 0xFFFFF8E1)
                    ),
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier
                        .size(170.dp)
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .fillMaxSize()
                    ) {
                        if(imageUri != null){
                            Text("Imagem selecionada!", color = Color.Black, textAlign = TextAlign.Center)
                            Text("Pronta para upload!", color = Color.Black, textAlign = TextAlign.Center, fontSize = 12.sp)
                        } else {
                            Image(
                                painter = painterResource(R.drawable.camera),
                                contentDescription = "Ícone de Câmera",
                                modifier = Modifier
                                    .size(65.dp)
                            )
                            Text(
                                text = "Carregar foto do pet",
                                fontSize = 20.sp,
                                textAlign = TextAlign.Center,
                                fontWeight = FontWeight.ExtraBold,
                                color = Color.Black
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        text = "NOME:",
                        fontSize = 14.sp
                    )
                    OutlinedTextField(
                        value = nome,
                        onValueChange = {nome = it},
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(55.dp),
                        shape = RoundedCornerShape(10.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            unfocusedLabelColor = Color(color = 0xFF4E342E),
                            focusedContainerColor = Color(color = 0xFFFFF8E1),
                            focusedBorderColor = Color(color = 0xFF4E342E),
                            unfocusedBorderColor = Color(color = 0xFF4E342E)
                        ),
                        textStyle = TextStyle(fontSize = 15.sp)
                    )
                }
                Row(
                    modifier = Modifier
                        .padding(top = 15.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(
                        modifier = Modifier.weight(1f)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                        ) {
                            Text(
                                text = "DESCRIÇÃO:",
                                fontSize = 14.sp,
                                modifier = Modifier.padding(bottom = 5.dp)
                            )
                            OutlinedTextField(
                                value = descricao,
                                onValueChange = {descricao = it},
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(150.dp),
                                shape = RoundedCornerShape(10.dp),
                                colors = OutlinedTextFieldDefaults.colors(
                                    unfocusedLabelColor = Color(color = 0xFF4E342E),
                                    focusedContainerColor = Color(color = 0xFFFFF8E1),
                                    focusedBorderColor = Color(color = 0xFF4E342E),
                                    unfocusedBorderColor = Color(color = 0xFF4E342E)
                                ),
                                textStyle = TextStyle(fontSize = 15.sp)
                            )
                        }
                        Spacer(modifier = Modifier.height(10.dp))

                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                        ) {
                            Text(
                                text = "STATUS (ID):", // Indicando que é um ID
                                fontSize = 14.sp,
                                modifier = Modifier.padding(bottom = 5.dp)
                            )
                            OutlinedTextField(
                                value = status,
                                onValueChange = {status = it},
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(55.dp),
                                shape = RoundedCornerShape(10.dp),
                                colors = OutlinedTextFieldDefaults.colors(
                                    unfocusedLabelColor = Color(color = 0xFF4E342E),
                                    focusedContainerColor = Color(color = 0xFFFFF8E1),
                                    focusedBorderColor = Color(color = 0xFF4E342E),
                                    unfocusedBorderColor = Color(color = 0xFF4E342E)
                                ),
                                textStyle = TextStyle(fontSize = 15.sp)
                            )
                        }
                        Spacer(modifier = Modifier.height(10.dp))

                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                        ) {
                            Text(
                                text = "TEMPERAMENTO (ID):", // Indicando que é um ID
                                fontSize = 14.sp,
                                modifier = Modifier.padding(bottom = 5.dp)
                            )
                            OutlinedTextField(
                                value = temperamento,
                                onValueChange = {temperamento = it},
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(55.dp),
                                shape = RoundedCornerShape(10.dp),
                                colors = OutlinedTextFieldDefaults.colors(
                                    unfocusedLabelColor = Color(color = 0xFF4E342E),
                                    focusedContainerColor = Color(color = 0xFFFFF8E1),
                                    focusedBorderColor = Color(color = 0xFF4E342E),
                                    unfocusedBorderColor = Color(color = 0xFF4E342E)
                                ),
                                textStyle = TextStyle(fontSize = 15.sp)
                            )
                        }
                    }
                    Spacer(modifier = Modifier.width(10.dp))

                    Column(
                        modifier = Modifier.weight(1f),
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Column(
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(end = 5.dp)
                            ) {
                                Text(
                                    text = "IDADE:",
                                    fontSize = 14.sp,
                                    modifier = Modifier
                                        .padding(bottom = 5.dp)
                                )
                                OutlinedTextField(
                                    value = idade,
                                    onValueChange = {idade = it},
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(55.dp),
                                    shape = RoundedCornerShape(10.dp),
                                    colors = OutlinedTextFieldDefaults.colors(
                                        unfocusedLabelColor = Color(color = 0xFF4E342E),
                                        focusedContainerColor = Color(color = 0xFFFFF8E1),
                                        focusedBorderColor = Color(color = 0xFF4E342E),
                                        unfocusedBorderColor = Color(color = 0xFF4E342E)
                                    ),
                                    textStyle = TextStyle(fontSize = 15.sp)
                                )
                            }
                            Column(
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(start = 5.dp)
                            ) {
                                Text(
                                    text = "SEXO:",
                                    fontSize = 14.sp,
                                    modifier = Modifier
                                        .padding(bottom = 5.dp)
                                )
                                OutlinedTextField(
                                    value = sexo,
                                    onValueChange = {sexo = it},
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(55.dp),
                                    shape = RoundedCornerShape(10.dp),
                                    colors = OutlinedTextFieldDefaults.colors(
                                        unfocusedLabelColor = Color(color = 0xFF4E342E),
                                        focusedContainerColor = Color(color = 0xFFFFF8E1),
                                        focusedBorderColor = Color(color = 0xFF4E342E),
                                        unfocusedBorderColor = Color(color = 0xFF4E342E)
                                    ),
                                    textStyle = TextStyle(fontSize = 15.sp)
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(10.dp))

                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                        ) {
                            Text(
                                text = "RAÇA:",
                                fontSize = 14.sp,
                                modifier = Modifier
                                    .padding(bottom = 5.dp)
                            )
                            OutlinedTextField(
                                value = raca,
                                onValueChange = {raca = it},
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(55.dp),
                                shape = RoundedCornerShape(10.dp),
                                colors = OutlinedTextFieldDefaults.colors(
                                    unfocusedLabelColor = Color(color = 0xFF4E342E),
                                    focusedContainerColor = Color(color = 0xFFFFF8E1),
                                    focusedBorderColor = Color(color = 0xFF4E342E),
                                    unfocusedBorderColor = Color(color = 0xFF4E342E)
                                ),
                                textStyle = TextStyle(fontSize = 15.sp)
                            )
                        }
                        Spacer(modifier = Modifier.height(10.dp))

                        Column(
                            modifier = Modifier
                                .fillMaxWidth()

                        ) {
                            Text(
                                text = "CONTATO DO DONO:",
                                fontSize = 14.sp,
                                modifier = Modifier
                                    .padding(bottom = 5.dp)
                            )
                            OutlinedTextField(
                                value = contato_do_dono,
                                onValueChange = {contato_do_dono = it},
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(55.dp),
                                shape = RoundedCornerShape(10.dp),
                                colors = OutlinedTextFieldDefaults.colors(
                                    unfocusedLabelColor = Color(color = 0xFF4E342E),
                                    focusedContainerColor = Color(color = 0xFFFFF8E1),
                                    focusedBorderColor = Color(color = 0xFF4E342E),
                                    unfocusedBorderColor = Color(color = 0xFF4E342E)
                                ),
                                textStyle = TextStyle(fontSize = 15.sp)
                            )
                        }
                        Spacer(modifier = Modifier.height(10.dp))

                        Column(
                            modifier = Modifier
                                .fillMaxWidth()

                        ) {
                            Text(
                                text = "VACINAS (ID):", // Indicando que é um ID
                                fontSize = 14.sp,
                                modifier = Modifier
                                    .padding(bottom = 5.dp)
                            )
                            OutlinedTextField(
                                value = vacina,
                                onValueChange = {vacina = it},
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(55.dp),
                                shape = RoundedCornerShape(10.dp),
                                colors = OutlinedTextFieldDefaults.colors(
                                    unfocusedLabelColor = Color(color = 0xFF4E342E),
                                    focusedContainerColor = Color(color = 0xFFFFF8E1),
                                    focusedBorderColor = Color(color = 0xFF4E342E),
                                    unfocusedBorderColor = Color(color = 0xFF4E342E)
                                ),
                                textStyle = TextStyle(fontSize = 15.sp)
                            )
                        }
                    }
                }
            }
            Button(
                onClick = onClick@{
                    // 1. Validação dos campos
                    if (nome.isBlank() || idade.isBlank() || sexo.isBlank() || raca.isBlank() ||
                        contato_do_dono.isBlank() || descricao.isBlank() ) { // Adicionei descricao
                        Toast.makeText(context, "Preencha todos os campos textuais obrigatórios.", Toast.LENGTH_LONG).show()
                        return@onClick
                    }

                    // 2. Validar e converter IDs que devem ser Int
                    val idStatusProcessoInt = status.toIntOrNull()
                    val idTemperamentoInt = temperamento.toIntOrNull()
                    val idVacinaInt = vacina.toIntOrNull()

                    // Validar se as conversões foram bem-sucedidas
                    if (idStatusProcessoInt == null) {
                        Toast.makeText(context, "O ID do Status deve ser um número válido.", Toast.LENGTH_LONG).show()
                        return@onClick
                    }
                    if (idTemperamentoInt == null) {
                        Toast.makeText(context, "O ID do Temperamento deve ser um número válido.", Toast.LENGTH_LONG).show()
                        return@onClick
                    }
                    if (idVacinaInt == null) {
                        Toast.makeText(context, "O ID das Vacinas deve ser um número válido.", Toast.LENGTH_LONG).show()
                        return@onClick
                    }

                    // Validar se a foto foi carregada com sucesso
                    if (fotoUrl.isNullOrBlank()) {
                        Toast.makeText(context, "Por favor, carregue uma foto para o pet.", Toast.LENGTH_LONG).show()
                        return@onClick
                    }

                    val idUsuarioInt = 1 // ou obtido de outro lugar

                    val animal = Animal(
                        nome = nome,
                        idade = idade,
                        sexo = sexo,
                        raca = raca,
                        especie = "dog", // Preencha com valor real se tiver
                        foto = fotoUrl!!, // Já é String?, então está ok aqui, fotoUrl é String?
                        localizacao = "dsadwadsdaf", // Preencha com valor real se tiver
                        celular_responsavel = contato_do_dono,
                        id_status_processo = idStatusProcessoInt, // Agora é Int (não Int?)
                        id_temperamento = idTemperamentoInt,     // Agora é Int (não Int?)
                        id_vacina = idVacinaInt,               // Agora é Int (não Int?)
                        id_status_saude = 1, // Assumindo que este é sempre 1 por enquanto
                        id_usuario = idUsuarioInt
                    )

                    // Verifique o nome do serviço correto. Se você está cadastrando Animal,
                    // o serviço deve ser para Animal, não Usuario.
                    // Exemplo: RetrofitFactory().getAnimalService().insert(animal)
                    // Substituí 'user' por 'animal' e 'InsertCadastro_Usuario_Service' por um placeholder.
                    // VOCÊ PRECISARÁ AJUSTAR ISSO PARA O SEU SERVIÇO DE ANIMAL REAL.
                    val call = RetrofitFactory().InserirAnimal().inserirAnimal(animal) // <<-- AJUSTAR AQUI

                    call.enqueue(object : Callback<Animal> { // <<-- O TIPO DE RETORNO TAMBÉM DEVE SER ANIMAL
                        override fun onResponse(call: Call<Animal>, response: Response<Animal>) {
                            if (response.isSuccessful) {
                                Toast.makeText(context, "Cadastro do animal realizado com sucesso!", Toast.LENGTH_LONG).show()
                                Log.i("API", "Animal cadastrado com sucesso: ${response.body()}")
                                // Opcional: Limpar campos ou navegar de volta
                                nome = ""
                                descricao = ""
                                status = ""
                                sexo = ""
                                idade = ""
                                raca = ""
                                fotoUrl = null
                                imageUri = null
                                contato_do_dono = ""
                                temperamento = ""
                                vacina = ""

                                navController?.popBackStack() // Exemplo de navegação de volta
                            } else {
                                Toast.makeText(context, "Erro ao cadastrar animal. Tente novamente.", Toast.LENGTH_LONG).show()
                                Log.e("API", "Erro ao cadastrar animal: ${response.code()} - ${response.errorBody()?.string()}")
                            }
                        }

                        override fun onFailure(call: Call<Animal>, t: Throwable) {
                            Toast.makeText(context, "Erro de conexão. Verifique sua internet.", Toast.LENGTH_LONG).show()
                            Log.e("API", "Falha na requisição de cadastro de animal: ${t.message}", t)
                        }
                    })
                },
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .padding(top = 15.dp)
                    .width(200.dp)
                    .height(61.dp),
                colors = ButtonDefaults.buttonColors(
                    Color(color = 0xFF4E342E)
                )
            ){
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Image(
                        painter = painterResource(R.drawable.dog),
                        contentDescription = "Ícone de Cachorro",
                        modifier = Modifier
                            .size(23.dp)
                    )
                    Text(
                        text = "CADASTRAR",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

// --- Funções Auxiliares Fora do Composable ---

private fun uploadSelectedImageToAzure(
    context: Context,
    scope: CoroutineScope,
    imageUri: Uri,
    onUploadComplete: (String?) -> Unit
) {
    scope.launch {
        Toast.makeText(context, "Iniciando upload da imagem...", Toast.LENGTH_SHORT).show()

        val file = getFileFromUri(context, imageUri)

        if (file == null) {
            Toast.makeText(context, "Não foi possível obter o arquivo da imagem.", Toast.LENGTH_LONG).show()
            onUploadComplete(null)
            return@launch
        }

        val storageAccount = "imgemurl"
        val sasToken = "sp=racwdli&st=2025-06-10T16:53:42Z&se=2025-06-11T00:53:42Z&sv=2024-11-04&sr=c&sig=ZXlwKgWS1v2HlefgV8z5nCym2U3GN6SFnkV91cmnlzs%3D"
        val containerName = "fotos"

        val uploadParams = UploadParams(file, storageAccount, sasToken, containerName)

        val result = uploadImageToAzure(uploadParams) // Esta função DEVE estar com Dispatchers.IO

        result.onSuccess { url ->
            Toast.makeText(context, "Upload bem-sucedido!", Toast.LENGTH_LONG).show()
            onUploadComplete(url)
        }.onFailure { exception ->
            Toast.makeText(context, "Erro no upload: ${exception.message}", Toast.LENGTH_LONG).show()
            Log.e("TelaCadastrarAnimais", "Erro no upload para Azure: ${exception.message}", exception)
            onUploadComplete(null)
        }
        file.delete()
    }
}

private fun getFileFromUri(context: Context, uri: Uri): File? {
    try {
        val inputStream: InputStream? = context.contentResolver.openInputStream(uri)
        inputStream?.use { input ->
            val tempFile = File.createTempFile("upload", ".tmp", context.cacheDir)
            FileOutputStream(tempFile).use { output ->
                input.copyTo(output)
            }
            return tempFile
        }
    } catch (e: Exception) {
        Log.e("TelaCadastrarAnimais", "Erro ao obter arquivo da URI: ${e.message}", e)
    }
    return null
}


@Preview(showSystemUi = true)
@Composable
private fun TelaCadastrarAnimaisPreview() {
    TelaCadastrarAnimais(navController = null)
}
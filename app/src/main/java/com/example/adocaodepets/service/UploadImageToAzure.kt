
package com.example.adocaodepets.service

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File
import java.net.URLEncoder

// Data class para parâmetros de upload
data class UploadParams(
    val file: File,
    val storageAccount: String,
    val sasToken: String,
    val containerName: String
)

// Função de upload para o Azure Blob Storage
suspend fun uploadImageToAzure(params: UploadParams): Result<String> {
    return withContext(Dispatchers.IO) { // <-- ESSA É A CHAVE!
        try {
            val client = OkHttpClient()

            val encodedFileName = URLEncoder.encode(params.file.name, "UTF-8").replace("+", "%20")
            val blobUrl = "https://${params.storageAccount}.blob.core.windows.net/${params.containerName}/$encodedFileName?${params.sasToken}"

            val mediaType = "application/octet-stream".toMediaTypeOrNull() // Ou determine o tipo real da imagem (e.g., "image/jpeg")
            val requestBody = params.file.readBytes().toRequestBody(mediaType)

            val request = Request.Builder()
                .url(blobUrl)
                .put(requestBody)
                .addHeader("x-ms-blob-type", "BlockBlob") // Importante para Azure Blob Storage
                .build()

            val response = client.newCall(request).execute()

            if (response.isSuccessful) {
                Log.d("UploadAzure", "Upload bem-sucedido! URL: $blobUrl")
                Result.success(blobUrl)
            } else {
                val errorBody = response.body?.string()
                Log.e("UploadAzure", "Erro no upload: ${response.code} - $errorBody")
                Result.failure(Exception("Falha no upload: ${response.code} - $errorBody"))
            }
        } catch (e: Exception) {
            Log.e("UploadAzure", "Exceção durante o upload: ${e.message}", e)
            Result.failure(e)
        }
    }
}
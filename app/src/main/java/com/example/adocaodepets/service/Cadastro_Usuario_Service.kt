package com.example.adocaodepets.service

import com.example.adocaodepets.model.Cadastro_Usuario
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface Cadastro_Usuario_Service {
    @Headers("Content-Type: application/json")
    @POST("usuario") // endpoint de POST (ex: /cadastro)
    fun insert(@Body user: Cadastro_Usuario): retrofit2.Call<Cadastro_Usuario>
}
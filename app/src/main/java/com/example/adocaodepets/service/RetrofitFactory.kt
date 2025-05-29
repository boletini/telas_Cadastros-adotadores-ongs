package com.example.adocaodepets.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
//

class RetrofitFactory {
    private val BASE_URL = "http://10.107.144.30:8080/v1/controle-pet/" //url

    private val retrofitFactory = Retrofit
        .Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getCadastro_Usuario_Service(): Cadastro_Usuario_Service{
        return retrofitFactory.create(Cadastro_Usuario_Service::class.java)
    }
}

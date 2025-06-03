package com.example.adocaodepets.service

import com.example.adocaodepets.model.Result
import com.example.adocaodepets.model.Usuario
import com.example.adocaodepets.model.UsuarioLogin
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.GET
import retrofit2.http.POST



interface Cadastro_Usuario_Service {
    @Headers("Content-Type: application/json")
    @POST("usuario") // endpoint de POST (ex: /cadastro)
    fun insert(@Body usuario: Usuario): Call<Usuario>



    @Headers("Content-Type: application/json")
    @GET("categoria")
    fun listarCategorias(): Call<Result>


    @Headers("Content-Type: application/json")
    @POST("login")
    fun insertLogin(@Body usuarioLogin: UsuarioLogin): Call<UsuarioLogin>

}
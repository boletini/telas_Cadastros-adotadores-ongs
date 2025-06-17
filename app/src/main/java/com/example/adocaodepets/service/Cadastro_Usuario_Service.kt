package com.example.adocaodepets.service

import com.example.adocaodepets.model.Animal
import com.example.adocaodepets.model.LoginResponse
import com.example.adocaodepets.model.Result
import com.example.adocaodepets.model.Usuario
import com.example.adocaodepets.model.UsuarioLogin
import com.example.adocaodepets.model.UsuarioResponse
import com.example.adocaodepets.model.resultListaAnimal
import com.example.adocaodepets.model.resultListaSexo
import com.example.adocaodepets.model.resultListaStatusProcesso
import com.example.adocaodepets.model.resultListaStatusSaude
import com.example.adocaodepets.model.resultListaTemperamento
import com.example.adocaodepets.model.resultListaVacina
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
    @POST("login")
    fun insertLogin(@Body usuarioLogin: UsuarioLogin): Call<LoginResponse>

    @Headers("Content-Type: application/json")
    @POST("animal")
    fun inserirAnimal(@Body animal: Animal): Call<Animal>


    @Headers("Content-Type: application/json")
    @GET("categoria")
    fun listarCategorias(): Call<Result>

    @Headers("Content-Type: application/json")
    @GET("status-processo")
    fun listarStatusProcesso(): Call<resultListaStatusProcesso>

    @Headers("Content-Type: application/json")
    @GET("temperamento")
    fun listarStatusTemperamento(): Call<resultListaTemperamento>

    @Headers("Content-Type: application/json")
    @GET("vacina")
    fun listarVacina(): Call<resultListaVacina>

    @Headers("Content-Type: application/json")
    @GET("status-saude")
    fun listarStatusSaude(): Call<resultListaStatusSaude>

    @Headers("Content-Type: application/json")
    @GET("sexo")
    fun listarSexo(): Call<resultListaSexo>






    @Headers("Content-Type: application/json")
    @GET("animal")
    fun listarAnimais(): Call<resultListaAnimal>



}
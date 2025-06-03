package com.example.adocaodepets.model

data class Usuario(
    val nome: String,
    val id_categoria: Int,
    val email: String,
    val endereco : String,
    val cnpj: String,
    val senha: String,
    val data_nascimento: String?,
    val cpf: String
)

data class UsuarioLogin (
    val email: String,
    val senha: String
)

data class Categoria (
    val id : Int = 0,
    val nome_categoria: String = ""
)
package com.example.adocaodepets.model

data class Usuario(
    val nome: String = "",
    val email: String = "",
    val senha: String = "",
    val cnpj: String = "",
    val cpf: String = "",
    val data_nascimento: String = "",
    val id_categoria: Int = 0
)

data class Categoria (
    val id : Int = 0,
    val nome_categoria: String = ""
)
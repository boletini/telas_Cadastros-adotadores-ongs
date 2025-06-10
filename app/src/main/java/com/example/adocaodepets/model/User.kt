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


data class Animal(
    val nome: String,
    val idade: String,
    val sexo: String,
    val raca: String,
    val especie: String,
    val foto: String,
    val localizacao: String,
    val celular_responsavel: String,
    val id_status_processo: Int,
    val id_temperamento: Int,
    val id_vacina: Int,
    val id_status_saude: Int,
    val id_usuario: Int
)



data class UsuarioLogin (
    val email: String,
    val senha: String
)

data class Categoria (
    val id : Int = 0,
    val nome_categoria: String = ""
)

data class homeAnimal (
    var id: Int = 0,
    var foto: String = ""
)

data class  homeStatusProcesso (
    var id: Int = 0,
    var status_processo: String = ""
)

data class homeStatusTemperamento (
    var id: Int = 0,
    var nome_temperamento: String = ""
)
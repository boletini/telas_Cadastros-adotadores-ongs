package com.example.adocaodepets.model

data class Result (
    var categorias: List<Categoria>? = null
)

data class resultListaAnimal (
    var animais: List<homeAnimal>? = null
)

data class resultListaStatusProcesso (
    var status_processo: List<homeStatusProcesso>? = null
)
data class resltListaTemperamento (
    var temperamento: List<homeStatusTemperamento>? = null
)




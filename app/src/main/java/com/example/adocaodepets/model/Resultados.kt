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
data class resultListaTemperamento (
    var temperamentos: List<homeStatusTemperamento>? = null
)

data class  resultListaVacina (
    var vacinas: List<homeVacina>? = null
)

data class resultListaStatusSaude (
    var status_saude: List<homeStatusSaude>? = null
)

data class resultListaSexo (
    var sexo: List<homeSexo>? = null
)






package com.example.adocaodepets.screens.components

import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MenuDefaults
import androidx.compose.material3.MenuItemColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.isDebugInspectorInfoEnabled
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun CategoriaItem(
    id: Int = 0,
    nome_categoria: String = "Sem Categoria"
){

    DropdownMenuItem(
        text = { Text(nome_categoria) },
        leadingIcon = {},
        onClick = {id},
        colors = MenuDefaults.itemColors(
            Color(color = 0xFFD9D9D9)
        )
    )

}


@Preview(showBackground = true)
@Composable
private fun CategoriaItemPreview(){
    CategoriaItem(
        id = 0,
        nome_categoria = "Tutor")


}
package com.iegabrielamistral.mentalcare

// Clase de datos que representa a un Usuario con propiedades comunes
data class Usuario(
    val nombre: String = "",   // Nombre del usuario, valor por defecto vacío
    val apellido: String = "", // Apellido del usuario, valor por defecto vacío
    val fecha: String = "",    // Fecha asociada al usuario (posiblemente de nacimiento o registro), valor por defecto vacío
    val correo: String = ""    // Correo electrónico del usuario, valor por defecto vacío
)


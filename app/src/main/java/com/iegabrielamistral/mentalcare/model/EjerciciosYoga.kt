package com.iegabrielamistral.mentalcare.model

data class EjerciciosYoga(
    val ejercicios: ArrayList<Ejercicio> = ArrayList()
)

data class Ejercicio(
    val nombre : String = "",
    val tiempo : String = "",
    val descripcion : String = "",
    val animacion : String = ""
)

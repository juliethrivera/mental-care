package com.iegabrielamistral.mentalcare.model

data class EjerciciosYoga(
    val ejercicios: ArrayList<Ejercicio> = ArrayList()
)

data class Ejercicio(
    val nombre : String = "",
    val tiempo : Int = 0,
    val descripcion : String = "",
    val animacion : String = ""
)
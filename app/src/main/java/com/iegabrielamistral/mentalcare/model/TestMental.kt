package com.iegabrielamistral.mentalcare.model

data class TestMental(
    val preguntas : ArrayList<Preguntas> = ArrayList()

)

data class Preguntas(
    val pregunta : String = "",
    val opcion1: String = "",
    val opcion2: String = "",
    val opcion3: String = "",
    val opcion4: String = "",
    val opcion5: String = "",




)

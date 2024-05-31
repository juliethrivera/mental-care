package com.iegabrielamistral.mentalcare.fragments

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ProgressBar
import android.widget.RadioGroup
import android.widget.TextView
import com.iegabrielamistral.mentalcare.R

class TestMentalBlankFragment : Fragment() {

    private lateinit var anterior : ImageButton
    private lateinit var testMental: TextView
    private lateinit var progreso : ProgressBar
    private lateinit var cantidadPreguntas : TextView
    private lateinit var pregunta : TextView
    private lateinit var opciones : RadioGroup
    private lateinit var siguientePregunta : Button

    var question : Int = 0

    val selecciones = mutableListOf(0,0,0,0)

    companion object {
        fun newInstance() = TestMentalBlankFragment()
    }

    private val viewModel: TestMentalBlankViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel



    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        anterior = view.findViewById(R.id.anterior)
        testMental = view.findViewById(R.id.testMental)
        progreso = view.findViewById(R.id.progreso)
        cantidadPreguntas = view.findViewById(R.id.cantidadPreguntas)
        pregunta = view.findViewById(R.id.pregunta)
        opciones = view.findViewById(R.id.opciones)
        siguientePregunta = view.findViewById(R.id.Siguiente)


        val jsonString = readJsonFromRaw(requireContext(), R.raw.preguntas)

        val gson = Gson()

        val test : Test = gson.fromJson(jsonString, object : TypeToken<Test>(){}.type)

        cargarPreguntas(test)



        siguientePregunta.setOnClickListener {
            question++
            if (question <= 20){
                obtenerSeleccion()
                cargarPregunta(test)
            }else{

            }
        }



    }



    fun obtenerSeleccion() {

        when(respuestas.checke)


    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_test_mental_blank, container, false)
    }

    fun actualizarProgreso(porcentaje : Int){
        progreso.progress = porcentaje
        if(porcentaje >= 100){

        }
    }




}

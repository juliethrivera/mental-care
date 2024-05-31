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
import android.widget.RadioButton
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
    private lateinit var opcion_1 : RadioButton
    private lateinit var opcion_2 : RadioButton
    private lateinit var opcion_3 : RadioButton
    private lateinit var opcion_4 : RadioButton
    private lateinit var opcion_5 : RadioButton
    private lateinit var siguiente : Button

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
        opcion_1 = view.findViewById(R.id.opcion_1)
        opcion_2 = view.findViewById(R.id.opcion_2)
        opcion_3= view.findViewById(R.id.opcion_3)
        opcion_4= view.findViewById(R.id.opcion_4)
        opcion_5= view.findViewById(R.id.opcion_5)
        siguiente= view.findViewById(R.id.Siguiente)


        val jsonString = readJsonFromRaw(requireContext(), R.raw.preguntas)

        val gson = Gson()

        val test: Test = gson.fromJson(jsonString, object : TypeToken<Test>(){}.type)

        cargarPreguntas(test)



        siguiente.setOnClickListener {
            question++
            if (question <= 20){
                obtenerSeleccion()
                cargarPregunta(test)
            }else{

            }
        }



    }



    fun obtenerSeleccion() {

        when(opciones.checkedRadioButtonId){
            opcion_1.id ->{
                selecciones[0] += 1
            }
            opcion_2.id ->{
                selecciones[1] += 1
            }
            opcion_3.id ->{
                selecciones[2] += 1
            }
            opcion_4.id ->{
                selecciones[3] += 1
            }
            opcion_5.id ->{
                selecciones[4] += 1
            }
        }


    }

    fun cargarPregunta(testMental : TestMental){

        opciones.clearCheck()

        pregunta.text = testMental.preguntas[question].pregunta
        opcion_1.text = testMental.preguntas[question].pregunta
        opcion_2.text = testMental.preguntas[question].pregunta
        opcion_3.text = testMental.preguntas[question].pregunta
        opcion_4.text = testMental.preguntas[question].pregunta
        opcion_5.text = testMental.preguntas[question].pregunta

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

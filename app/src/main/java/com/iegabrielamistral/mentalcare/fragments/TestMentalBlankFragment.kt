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
        siguientePregunta = view.findViewById(R.id.siguientePregunta)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_test_mental_blank, container, false)
    }


}